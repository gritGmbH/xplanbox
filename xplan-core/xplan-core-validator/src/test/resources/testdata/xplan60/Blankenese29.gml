<?xml version="1.0" encoding="utf-8"?>
<!--
Testplan für Version 6.0, der folgende Neuerungen beinhaltet: 
Neues Attribut aendertPlan (CR-058)
Umbenennung <<Enumeration>> XP_RechtscharakterPlanaenderung in XP_Aenderungsarten (CR-058)
Neues Attribut verfahren an BP_Bereich (CR-014)
Neues Attribut versionBauNVO (XP_GesetzlicheGrundlage), das die Attribute versionBauNVODatum und versionBauNVOText ersetzt. (CR-039)
Neues Attribut versionBauGB (XP_GesetzlicheGrundlage), das die Attribute versionBauGBDatum und versionBauGBText ersetzt. (CR-039)
Attribut zweckbestimmung mit neuem Datentyp XP_KomplexeZweckbestVerEntsorgung (CR-025)
SO_SchutzgebietNaturschutzrecht in LP_SchutzBestimmterTeileVonNaturUndLandschaft umgewandelt
-->
<xplan:XPlanAuszug xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="Gml_4ECB5087-4509-447B-A14A-BE973B676663" xsi:schemaLocation="http://www.xplanung.de/xplangml/6/0 http://www.xplanungwiki.de/upload/XPlanGML/6.0/Schema/XPlanung-Operationen.xsd" xmlns:xplan="http://www.xplanung.de/xplangml/6/0">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>552874.001 5934779.7367</gml:lowerCorner>
      <gml:upperCorner>553340.636 5935085.6293</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_4e02cb5a-77f2-4329-a668-2af01f3db0c0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552874.001 5934779.7367</gml:lowerCorner>
          <gml:upperCorner>553340.636 5935085.6293</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Blankenese29_Testplan_60</xplan:name>
      <xplan:nummer>29</xplan:nummer>
      <xplan:beschreibung>Der Bebauungsplan Blankenese 29 für den Geltungsbereich nordöstlich des Süllbergs zwischen Süllbergsterrasse, Wilmans .Park und Kahlkamp/Süllbergsweg (Bezirk Altona, Ortsteil 222) wird festgestellt.
Das Gebiet wird wie folgt begrenzt: Süllbergterrasse - R</xplan:beschreibung>
      <xplan:technHerstellDatum>2015-05-05</xplan:technHerstellDatum>
      <xplan:aendertPlan>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>BSBlankenese</xplan:planName>
          <xplan:aenderungsArt>2000</xplan:aenderungsArt>
        </xplan:XP_VerbundenerPlan>
      </xplan:aendertPlan>
      <xplan:aendertPlan>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>TB556</xplan:planName>
          <xplan:aenderungsArt>2000</xplan:aenderungsArt>
        </xplan:XP_VerbundenerPlan>
      </xplan:aendertPlan>
      <xplan:aendertPlan>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>FLPL-BL11</xplan:planName>
          <xplan:aenderungsArt>2000</xplan:aenderungsArt>
        </xplan:XP_VerbundenerPlan>
      </xplan:aendertPlan>
      <xplan:aendertPlan>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>FLPL-BL12</xplan:planName>
          <xplan:aenderungsArt>2000</xplan:aenderungsArt>
        </xplan:XP_VerbundenerPlan>
      </xplan:aendertPlan>
      <xplan:aendertPlan>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>FLPL-BL13</xplan:planName>
          <xplan:aenderungsArt>2000</xplan:aenderungsArt>
        </xplan:XP_VerbundenerPlan>
      </xplan:aendertPlan>
      <xplan:aendertPlan>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>FLPL-BL21</xplan:planName>
          <xplan:aenderungsArt>2000</xplan:aenderungsArt>
        </xplan:XP_VerbundenerPlan>
      </xplan:aendertPlan>
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon gml:id="Gml_8D85DED5-5E60-4F4B-9D7B-A78025E35D0C" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553320.571 5934992.049 553335.751 5935010.241 553339.4069 5935014.3468 
553340.636 5935015.727 553326.028 5935033.877 553320.527 5935037.519 
553299.972 5935051.125 553294.9752 5935054.3542 553258.2483 5935066.594 
553242.1183 5935067.0238 553230.74 5935070.32 553225.953 5935070.404 
553226.5451 5935072.351 553222.6889 5935075.3171 553213.8368 5935081.5451 
553204.9108 5935084.2318 553192.8648 5935085.6293 553187.5621 5935085.415 
553185.766 5935084.4463 553185.7661 5935082.4042 553150.7498 5935079.1018 
553111.7812 5935075.8453 553094.6831 5935073.8038 553082.2072 5935068.9619 
553070.8092 5935062.0819 553059.4542 5935052.5978 553044.519 5935036.6119 
553031.1259 5935019.6031 553014.4569 5934994.3281 553009.0721 5934988.229 
553000.9072 5934982.2861 552994.023 5934979.92 552986.924 5934979.8108 
552972.5732 5934983.3912 552948.1441 5934990.6031 552933.903 5934994.547 
552886.231 5935034.3988 552879.9168 5935039.631 552874.001 5935034.1218 
552883.0332 5935018.6352 552888.6291 5935009.3851 552931.1082 5934955.6171 
552952.0543 5934944.809 552965.2372 5934938.011 552986.7258 5934926.9259 
552987.0499 5934926.758 552988.971 5934925.5043 553000.537 5934913.1428 
553014.8672 5934897.825 553016.8663 5934896.1149 553025.9219 5934891.6242 
553061.456 5934881.3049 553078.5358 5934871.3361 553079.1448 5934870.981 
553079.8868 5934870.4579 553083.1312 5934868.1733 553089.2422 5934863.863 
553095.0098 5934859.7981 553098.2191 5934856.3621 553113.2868 5934838.659 
553114.8991 5934836.7652 553116.9569 5934834.0711 553129.3301 5934817.8982 
553133.7618 5934806.3958 553135.2258 5934802.2999 553137.6733 5934793.0499 
553139.3793 5934781.6018 553139.6975 5934779.7367 553143.3258 5934780.301 
553144.2872 5934782.0122 553145.296 5934783.8069 553150.7331 5934794.6412 
553155.0143 5934802.3039 553159.147 5934807.7382 553162.441 5934811.4292 
553168.3149 5934817.5809 553169.8623 5934819.3669 553172.5612 5934823.0972 
553174.5852 5934826.8679 553176.411 5934831.035 553177.5899 5934834.7679 
553177.5969 5934834.7899 553177.8399 5934835.5592 553178.832 5934839.171 
553179.8241 5934841.5919 553190.2829 5934839.6359 553199.1931 5934837.8829 
553205.7229 5934836.5799 553205.8598 5934836.5522 553206.9249 5934836.3399 
553205.0191 5934840.1278 553205.0058 5934840.1548 553204.4312 5934841.2961 
553201.1811 5934848.2598 553201.1738 5934848.276 553200.6071 5934849.4898 
553197.2732 5934859.2262 553193.5279 5934867.7461 553193.5161 5934867.7722 
553194.405 5934868.7579 553198.4329 5934873.2231 553200.2872 5934874.774 
553200.3031 5934874.7871 553204.8719 5934878.6078 553212.7789 5934883.967 
553212.8428 5934884.0081 553216.7411 5934886.5352 553216.799 5934886.5729 
553220.3342 5934888.8643 553222.9909 5934890.5868 553223.3448 5934890.8159 
553224.8869 5934891.8158 553228.6571 5934898.0069 553229.5599 5934899.701 
553229.601 5934899.7782 553231.1031 5934902.5978 553231.1429 5934902.5978 
553242.0078 5934917.1572 553248.8602 5934926.0078 553255.778 5934933.7251 
553259.1392 5934937.0409 553259.8742 5934936.088 553262.6892 5934937.7119 
553263.147 5934938.0008 553263.152 5934938.0039 553264.4982 5934938.8532 
553268.5681 5934941.4213 553274.334 5934945.2999 553274.3379 5934945.3022 
553279.481 5934948.762 553284.7991 5934952.453 553291.8551 5934958.4618 
553291.938 5934958.532 553296.3542 5934962.2929 553297.422 5934963.64 
553302.95 5934970.42 553320.571 5934992.049 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:texte xlink:href="#GML_6d3d0605-1142-42b3-a412-6d7004f8ede0" />
      <xplan:texte xlink:href="#GML_7b5fc84e-19e1-4859-877b-c21380a35199" />
      <xplan:texte xlink:href="#GML_316a4f70-1ec7-43e3-874e-4512ba542a78" />
      <xplan:texte xlink:href="#GML_3a49acbe-ac34-43f4-aaff-4714285df683" />
      <xplan:texte xlink:href="#GML_71be06a2-b107-4201-a68d-ee52d8d1af53" />
      <xplan:texte xlink:href="#GML_b53df60c-050a-4134-af60-c8bc6176f136" />
      <xplan:texte xlink:href="#GML_8576d16d-cd52-47d5-bff1-69d7ed57a5c9" />
      <xplan:texte xlink:href="#GML_f0374205-9f3e-4faa-9deb-0e24f49f2eef" />
      <xplan:texte xlink:href="#GML_b4be074c-b09a-4d1c-8bb0-46097cf8e410" />
      <xplan:texte xlink:href="#GML_6818d377-335e-4ce3-8e08-d1195d504ad7" />
      <xplan:texte xlink:href="#GML_c0b8e69e-e02b-4667-8d04-dc323bab8ea7" />
      <xplan:texte xlink:href="#GML_43a887c4-c8fc-4a7c-91eb-2e800b2e59f7" />
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>02000000</xplan:ags>
          <xplan:gemeindeName>Freie und Hansestadt Hamburg</xplan:gemeindeName>
          <xplan:ortsteilName>222</xplan:ortsteilName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:plangeber>
        <xplan:XP_Plangeber>
          <xplan:name>222</xplan:name>
        </xplan:XP_Plangeber>
      </xplan:plangeber>
      <xplan:planArt>1000</xplan:planArt>      
      <xplan:rechtsstand>3000</xplan:rechtsstand>
      <xplan:rechtsverordnungsDatum>1991-07-02</xplan:rechtsverordnungsDatum>
      <xplan:versionBauNVO>
        <xplan:XP_GesetzlicheGrundlage>
          <xplan:datum>1990-01-01</xplan:datum>
        </xplan:XP_GesetzlicheGrundlage>     
      </xplan:versionBauNVO>
      <xplan:versionBauGB>
        <xplan:XP_GesetzlicheGrundlage>
          <xplan:datum>1986-12-08</xplan:datum>
        </xplan:XP_GesetzlicheGrundlage>        
      </xplan:versionBauGB>      
      <xplan:bereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_6d3d0605-1142-42b3-a412-6d7004f8ede0">
      <xplan:schluessel>§2 Nr.5.7</xplan:schluessel>
      <xplan:text>Bei Verblendung mit Vormauersteinen sind rote Ziegelsteine zu verwenden.

</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_7b5fc84e-19e1-4859-877b-c21380a35199">
      <xplan:schluessel>§2 Nr.5.1</xplan:schluessel>
      <xplan:text>Es sind nur Dächer mit einer Neigung bis maximal 45 Grad zulässig. Staffelgeschosse sind unzulässig.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_316a4f70-1ec7-43e3-874e-4512ba542a78">
      <xplan:schluessel>§2 Nr.2</xplan:schluessel>
      <xplan:text>Ausnahmen nach § 3 Absatz 3 Nummern 1 und 2 der Baunutzungsverordnung in der Fassung vom 23. Januar 1990 (Bundesgesetzblatt 1 Seite 133) werden ausgeschlossen.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_3a49acbe-ac34-43f4-aaff-4714285df683">
      <xplan:schluessel>§2 Nr.1</xplan:schluessel>
      <xplan:text>In dem nach § 172 des Baugesetzbuchs als "Erhaltungsbereich" bezeichneten Gebiet bedürfen zur Erhaltung der städtebaulichen  Eigenart  des  Gebiets  auf  Grund  seiner städtebaulichen Gestalt der Abbruch, die Änderung, die Nutzungsänderung oder die Errichtung baulicher Anlagen einer Genehmigung, und zwar auch dann, wenn nach der Baufreistellungsverordnung vom 5. Januar 1988 mit der Änderung vom 25. September 1990 (Hamburgisches Gesetz- und Verordnungsblatt 1988 Seite 1, 1990 Seite 216) in der jeweils geltenden Fassung eine Genehmigung nicht erforderlich ist. Die Genehmigung zum Abbruch, zur Änderung oder .zur Nutzungsänderung darf nur versagt werden, wenn die bauliche Anlage allein oder im Zusammenhang mit anderen baulichen Anlagen das Ortsbild, die Stadtgestalt oder das Landschaftsbild prägt oder sonst von städtebaulicher, insbesondere baugeschichtlicher Bedeutung ist. Die Genehmigung zur Errichtung der baulichen Anlage darf nur versagt werden, wenn die städtebauliche Gestalt des Gebiets durch  die beabsichtigte bauliche Anlage beeinträchtigt wird.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_71be06a2-b107-4201-a68d-ee52d8d1af53">
      <xplan:schluessel>§2 Nr.4</xplan:schluessel>
      <xplan:text>Die zulässige Geschoßfläche je Gebäude ist die Summe der Geschoßflächen der Vollgeschosse und des Dachgeschosses bei vollständiger Ausnutzung der überbaubaren Grundstücksfläche.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_b53df60c-050a-4134-af60-c8bc6176f136">
      <xplan:schluessel>§2 Nr.5.6</xplan:schluessel>
      <xplan:text>Bei Putzbauten sind helle Farbtöne zu verwenden.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_8576d16d-cd52-47d5-bff1-69d7ed57a5c9">
      <xplan:schluessel>§2 Nr.5</xplan:schluessel>
      <xplan:text>Im „Erhaltungsbereich" gelten nachstehende gestalterische Anforderungen:</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_f0374205-9f3e-4faa-9deb-0e24f49f2eef">
      <xplan:schluessel>§2 Nr.3</xplan:schluessel>
      <xplan:text>Für Bäume, die einen Stammumfang von mehr als 80 cm (in 1 m Höhe über dem Erdboden gemessen) aufweisen, sind bei Abgang Ersatzanpflanzungen mit einheimischen standortgerechten  Arten vorzunehmen.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_b4be074c-b09a-4d1c-8bb0-46097cf8e410">
      <xplan:schluessel>§2 Nr.5.4</xplan:schluessel>
      <xplan:text>Verglaste Fassadenflächen dürfen 60 vom Hundert (v. H.) der jeweiligen Gebäudeseite nicht überschreiten.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_6818d377-335e-4ce3-8e08-d1195d504ad7">
      <xplan:schluessel>§2 Nr.5.5</xplan:schluessel>
      <xplan:text>Auskragungen dürfen über Keller- und Stützwänden nicht mehr als 1,8m tief und 3,5 m lang sein. Überschreitungen der Länge sind zulässig, wenn dadurch die Hälfte der jeweiligen  Gebäudeseite nicht überschritten wird.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_c0b8e69e-e02b-4667-8d04-dc323bab8ea7">
      <xplan:schluessel>§2 Nr.5.2</xplan:schluessel>
      <xplan:text>Die Fenster sind kleinmaßstäblich zu gliedern; es  sind keine liegende Formate zu verwenden.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_TextAbschnitt gml:id="GML_43a887c4-c8fc-4a7c-91eb-2e800b2e59f7">
      <xplan:schluessel>§2 Nr.5.3</xplan:schluessel>
      <xplan:text>Bei Gebäuden in Hanglage sind geschlossene Stützmauern zu errichten. Aufgeständerte Gebäude und Plattformen sind unzulässig. Kellergeschosse, die zur Talseite über die Geländeoberfläche hinausragen, sind gestalterisch gegenüber den übrigen Geschossen so abzusetzen, daß das Erscheinungsbild als Sockelzone optisch wirksam wird.</xplan:text>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
    </xplan:XP_TextAbschnitt>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552874.001 5934779.7367</gml:lowerCorner>
          <gml:upperCorner>553340.636 5935085.6293</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:Polygon gml:id="Gml_037F24B3-A15C-4F1D-B37F-EB25A46B26A8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553320.571 5934992.049 553335.751 5935010.241 553339.4069 5935014.3468 
553340.636 5935015.727 553326.028 5935033.877 553320.527 5935037.519 
553299.972 5935051.125 553294.9752 5935054.3542 553258.2483 5935066.594 
553242.1183 5935067.0238 553230.74 5935070.32 553225.953 5935070.404 
553226.5451 5935072.351 553222.6889 5935075.3171 553213.8368 5935081.5451 
553204.9108 5935084.2318 553192.8648 5935085.6293 553187.5621 5935085.415 
553185.766 5935084.4463 553185.7661 5935082.4042 553150.7498 5935079.1018 
553111.7812 5935075.8453 553094.6831 5935073.8038 553082.2072 5935068.9619 
553070.8092 5935062.0819 553059.4542 5935052.5978 553044.519 5935036.6119 
553031.1259 5935019.6031 553014.4569 5934994.3281 553009.0721 5934988.229 
553000.9072 5934982.2861 552994.023 5934979.92 552986.924 5934979.8108 
552972.5732 5934983.3912 552948.1441 5934990.6031 552933.903 5934994.547 
552886.231 5935034.3988 552879.9168 5935039.631 552874.001 5935034.1218 
552883.0332 5935018.6352 552888.6291 5935009.3851 552931.1082 5934955.6171 
552952.0543 5934944.809 552965.2372 5934938.011 552986.7258 5934926.9259 
552987.0499 5934926.758 552988.971 5934925.5043 553000.537 5934913.1428 
553014.8672 5934897.825 553016.8663 5934896.1149 553025.9219 5934891.6242 
553061.456 5934881.3049 553078.5358 5934871.3361 553079.1448 5934870.981 
553079.8868 5934870.4579 553083.1312 5934868.1733 553089.2422 5934863.863 
553095.0098 5934859.7981 553098.2191 5934856.3621 553113.2868 5934838.659 
553114.8991 5934836.7652 553116.9569 5934834.0711 553129.3301 5934817.8982 
553133.7618 5934806.3958 553135.2258 5934802.2999 553137.6733 5934793.0499 
553139.3793 5934781.6018 553139.6975 5934779.7367 553143.3258 5934780.301 
553144.2872 5934782.0122 553145.296 5934783.8069 553150.7331 5934794.6412 
553155.0143 5934802.3039 553159.147 5934807.7382 553162.441 5934811.4292 
553168.3149 5934817.5809 553169.8623 5934819.3669 553172.5612 5934823.0972 
553174.5852 5934826.8679 553176.411 5934831.035 553177.5899 5934834.7679 
553177.5969 5934834.7899 553177.8399 5934835.5592 553178.832 5934839.171 
553179.8241 5934841.5919 553190.2829 5934839.6359 553199.1931 5934837.8829 
553205.7229 5934836.5799 553205.8598 5934836.5522 553206.9249 5934836.3399 
553205.0191 5934840.1278 553205.0058 5934840.1548 553204.4312 5934841.2961 
553201.1811 5934848.2598 553201.1738 5934848.276 553200.6071 5934849.4898 
553197.2732 5934859.2262 553193.5279 5934867.7461 553193.5161 5934867.7722 
553194.405 5934868.7579 553198.4329 5934873.2231 553200.2872 5934874.774 
553200.3031 5934874.7871 553204.8719 5934878.6078 553212.7789 5934883.967 
553212.8428 5934884.0081 553216.7411 5934886.5352 553216.799 5934886.5729 
553220.3342 5934888.8643 553222.9909 5934890.5868 553223.3448 5934890.8159 
553224.8869 5934891.8158 553228.6571 5934898.0069 553229.5599 5934899.701 
553229.601 5934899.7782 553231.1031 5934902.5978 553231.1429 5934902.5978 
553242.0078 5934917.1572 553248.8602 5934926.0078 553255.778 5934933.7251 
553259.1392 5934937.0409 553259.8742 5934936.088 553262.6892 5934937.7119 
553263.147 5934938.0008 553263.152 5934938.0039 553264.4982 5934938.8532 
553268.5681 5934941.4213 553274.334 5934945.2999 553274.3379 5934945.3022 
553279.481 5934948.762 553284.7991 5934952.453 553291.8551 5934958.4618 
553291.938 5934958.532 553296.3542 5934962.2929 553297.422 5934963.64 
553302.95 5934970.42 553320.571 5934992.049 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:geltungsbereich>
      <xplan:refScan>
        <xplan:XP_ExterneReferenz>
          <xplan:georefURL>Blankenese29.pgw</xplan:georefURL>
          <xplan:referenzName>Blankenese29</xplan:referenzName>
          <xplan:referenzURL>Blankenese29.png</xplan:referenzURL>
        </xplan:XP_ExterneReferenz>
      </xplan:refScan>
      <xplan:planinhalt xlink:href="#GML_9270e882-25b2-4dd2-8f3c-ce2d7c0541c7" />
      <xplan:planinhalt xlink:href="#GML_279c6a95-6211-4748-bb72-2edab8a0baca" />
      <xplan:planinhalt xlink:href="#GML_4b97d1c5-89ef-4fe9-a1f8-94a5b06f3144" />
      <xplan:planinhalt xlink:href="#GML_f2191523-0f54-4964-a6b2-b722842fa561" />
      <xplan:planinhalt xlink:href="#GML_59353d66-9104-454a-b113-314565fd9267" />
      <xplan:planinhalt xlink:href="#GML_3d0ff57e-a9b4-4a3c-bdfb-0de53a973b43" />
      <xplan:planinhalt xlink:href="#GML_504d163f-8c5f-454f-a223-314236cf807d" />
      <xplan:planinhalt xlink:href="#GML_3a693dcd-9e1b-40a1-88c5-b1c92858b2ee" />
      <xplan:planinhalt xlink:href="#GML_4aee750d-ade4-4c62-bf12-0d2df01f4cb3" />
      <xplan:planinhalt xlink:href="#GML_020de115-3e22-4d75-beff-6a946e803528" />
      <xplan:planinhalt xlink:href="#GML_a7a1fbfb-7f16-46cb-b777-b601262f9d70" />
      <xplan:planinhalt xlink:href="#GML_4554843f-6bde-4807-835d-c3449e36e56d" />
      <xplan:planinhalt xlink:href="#GML_362ffa88-bca9-452f-9975-dbd438799fc5" />
      <xplan:planinhalt xlink:href="#GML_33ea7edf-8b8a-4f4c-a1d6-f166e89e4837" />
      <xplan:planinhalt xlink:href="#GML_4268ceb8-821c-4cab-9aa2-393596c52357" />
      <xplan:planinhalt xlink:href="#GML_e34643be-758c-466c-8e04-dbe2fc820932" />
      <xplan:planinhalt xlink:href="#GML_0e61f84f-4a6f-44f0-9b4b-3b0ba33c82b2" />
      <xplan:planinhalt xlink:href="#GML_919adfd5-d0e0-467b-9f1e-2d1406abcb9d" />
      <xplan:planinhalt xlink:href="#GML_62ac6793-53d6-494d-a202-c30d30dbd8a7" />
      <xplan:planinhalt xlink:href="#GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5" />
      <xplan:planinhalt xlink:href="#GML_5a5f4648-b0b7-4de0-be18-5bfd2170bbcd" />
      <xplan:planinhalt xlink:href="#GML_b7d34767-ee95-4d67-9410-0d02e9ca8daa" />
      <xplan:planinhalt xlink:href="#GML_eecb61a3-904e-49af-b5c6-f970bbc9b575" />
      <xplan:planinhalt xlink:href="#GML_38ff6c4b-667c-4186-93ac-2e654bfd62e7" />
      <xplan:planinhalt xlink:href="#GML_dc87f173-784c-4b93-876a-ff853cd2d759" />
      <xplan:planinhalt xlink:href="#GML_c1fc4d1a-7ac6-41c9-8b77-c82b074101c9" />
      <xplan:planinhalt xlink:href="#GML_c8ee2d13-db90-4144-81bc-0c156911b4f5" />
      <xplan:planinhalt xlink:href="#GML_c43ec757-6459-4d49-a9e8-23cb8242782f" />
      <xplan:planinhalt xlink:href="#GML_89bf84bc-3686-467d-b144-895a1e432dce" />
      <xplan:planinhalt xlink:href="#GML_473aaa80-8378-46f7-97fa-75d210f8d2ca" />
      <xplan:planinhalt xlink:href="#GML_84db8d8f-fa25-4cb4-ad12-9b3ec8bc62f8" />
      <xplan:planinhalt xlink:href="#GML_5d1a4b8c-78a9-4a48-a1dd-d98e08bc6e96" />
      <xplan:planinhalt xlink:href="#GML_623528b6-4a6b-4e8b-a719-dab1ea62d223" />
      <xplan:planinhalt xlink:href="#GML_4c803914-471b-4f49-aafe-b02af0d2fad7" />
      <xplan:planinhalt xlink:href="#GML_0ef74ac8-a0fa-451b-8cf6-a29ae1bfd0a7" />
      <xplan:planinhalt xlink:href="#GML_1e12d354-6480-4756-bf94-1cab0c5cbd0c" />
      <xplan:planinhalt xlink:href="#GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e" />
      <xplan:planinhalt xlink:href="#GML_c7c478df-6f54-421d-9bec-905aca72dc9d" />
      <xplan:planinhalt xlink:href="#GML_6b206f9d-642e-4994-8d08-910f780396c3" />
      <xplan:planinhalt xlink:href="#GML_7d342af4-c53d-4e71-bc76-be962c12437c" />
      <xplan:planinhalt xlink:href="#GML_cb0cc7f0-678b-41e8-a97c-cb974d28d57a" />
      <xplan:planinhalt xlink:href="#GML_dbef154f-2649-45e1-8bac-cef02e300755" />
      <xplan:planinhalt xlink:href="#GML_3a42a99f-56f0-44ea-90a1-e1fc48ce5159" />
      <xplan:planinhalt xlink:href="#GML_56b77369-2f25-494e-a4e3-8d9e1c71f1fe" />
      <xplan:planinhalt xlink:href="#GML_357b57be-7114-4abc-af2a-d73f1c77dbfc" />
      <xplan:planinhalt xlink:href="#GML_5d5fe6d3-589a-45c1-a07d-6edec526d023" />
      <xplan:planinhalt xlink:href="#GML_5dcd6491-5653-4466-ad5d-75a241fa15de" />
      <xplan:planinhalt xlink:href="#GML_0099d02c-ea9f-4b35-9cc1-b80b945b5593" />
      <xplan:planinhalt xlink:href="#GML_7657b8de-b5d6-48f0-834a-c7ec8b5a10ac" />
      <xplan:planinhalt xlink:href="#GML_9cddeaff-df57-49d7-bfd0-f03e3ef2d4ab" />
      <xplan:planinhalt xlink:href="#GML_f9adc0fa-d2db-484d-9f3b-e541c0b713e5" />
      <xplan:planinhalt xlink:href="#GML_41bba10d-324d-4b86-953f-fac179070f1e" />
      <xplan:planinhalt xlink:href="#GML_7f31863d-6010-4a7c-a4e2-25f53f827ea9" />
      <xplan:planinhalt xlink:href="#GML_b51cde7a-a482-45b0-b85b-dfcb5cf488a5" />
      <xplan:planinhalt xlink:href="#GML_acb722ac-5885-492e-9e5c-9de2388498cb" />
      <xplan:planinhalt xlink:href="#GML_ce62a073-cf15-40b0-b3c0-b08ddb2eaf4c" />
      <xplan:planinhalt xlink:href="#GML_1a037d7e-8fdf-4d9c-8f3a-c05ad6bedcb8" />
      <xplan:planinhalt xlink:href="#GML_d81a5d6f-ca45-4fb9-a3d0-cb2312b39072" />
      <xplan:planinhalt xlink:href="#GML_dd8213ff-fe5f-42ad-9f8e-7fe102232567" />
      <xplan:planinhalt xlink:href="#GML_c2bb0da0-c6e1-4b54-ad54-f27fa4cbb721" />
      <xplan:planinhalt xlink:href="#GML_54ea0386-f17e-495a-8e77-b00d9e8f33b5" />
      <xplan:planinhalt xlink:href="#GML_643c4550-2e95-4b09-9102-f35090fff58e" />
      <xplan:planinhalt xlink:href="#GML_a140ed1c-048a-4d38-925a-e87902ce91d0" />
      <xplan:planinhalt xlink:href="#GML_4ca345b0-df0e-405a-acac-8b9339714a23" />
      <xplan:planinhalt xlink:href="#GML_f2dcf196-944c-4380-9437-2b4bc283c76f" />
      <xplan:planinhalt xlink:href="#GML_59f8b00f-304a-4e0e-8373-8ab3d92c0ddb" />
      <xplan:planinhalt xlink:href="#GML_3b5686be-be43-40e1-94be-fdb47f41a0e3" />
      <xplan:planinhalt xlink:href="#GML_7436bb17-6b66-4d81-971c-53a60615019a" />
      <xplan:planinhalt xlink:href="#GML_a0db7928-ba50-4e60-817b-b99cb0ce2932" />
      <xplan:planinhalt xlink:href="#GML_7d73c1ad-5988-426a-9615-686112ef6ad9" />
      <xplan:planinhalt xlink:href="#GML_829bf8bd-d0e5-413d-b175-eccea06b696c" />
      <xplan:planinhalt xlink:href="#GML_2786bdeb-0989-4d34-b773-2bb9eec5a21c" />
      <xplan:planinhalt xlink:href="#GML_da98b111-5052-442e-b1b1-131accdd40ec" />
      <xplan:planinhalt xlink:href="#GML_69a36291-20ba-4316-a7c5-37ef5323027e" />
      <xplan:planinhalt xlink:href="#GML_1850466b-b1dc-4744-b9f4-06426c8b3ace" />
      <xplan:planinhalt xlink:href="#GML_cc10aa85-21a6-46f4-872c-b2098fe715a3" />
      <xplan:planinhalt xlink:href="#GML_dcbca103-b877-45bd-837e-1fb75bb692b0" />
      <xplan:planinhalt xlink:href="#GML_45fefdb5-370b-4239-a343-b3a0172c32c0" />
      <xplan:planinhalt xlink:href="#GML_4ee3d8ee-0238-4bbf-850b-4947561221fa" />
      <xplan:planinhalt xlink:href="#GML_a7d50438-df48-431f-86b5-cb7013f12614" />
      <xplan:planinhalt xlink:href="#GML_e753c661-3c9c-4628-8ee3-77ab9f55e843" />
      <xplan:planinhalt xlink:href="#GML_1ceb3413-e1db-4284-953c-4ca527fcf01c" />
      <xplan:planinhalt xlink:href="#GML_9f045ee5-643a-44b2-b9b8-2bf190ff3189" />
      <xplan:planinhalt xlink:href="#GML_b6bda54f-4ad9-4927-8274-46730769d56f" />
      <xplan:planinhalt xlink:href="#GML_0dbe3df4-4c94-4324-aa25-79c3f71825c6" />
      <xplan:planinhalt xlink:href="#GML_816da885-bbc3-4320-90e1-2cfb96c5513d" />
      <xplan:planinhalt xlink:href="#GML_c76af1c9-22c1-4d7b-a025-9598590f1497" />
      <xplan:planinhalt xlink:href="#GML_e33890b6-857b-47ca-9ab7-e66a86e56b60" />
      <xplan:planinhalt xlink:href="#GML_6b56aca0-b58b-4c68-aeac-46c13ea81ce7" />
      <xplan:planinhalt xlink:href="#GML_a9f7ac0a-9d98-4f82-81ad-6ebeb13ae18f" />
      <xplan:planinhalt xlink:href="#GML_98c27262-2fb4-49f7-84d2-eec597062863" />
      <xplan:planinhalt xlink:href="#GML_9a4c52e1-2af1-4988-aeb0-d16381fedd5a" />
      <xplan:planinhalt xlink:href="#GML_e49decfd-bd31-439d-9241-41e7a5836627" />
      <xplan:planinhalt xlink:href="#GML_22fb4f62-11a2-4ee9-9823-e99f0582359a" />
      <xplan:planinhalt xlink:href="#GML_7b362be2-9b58-4fb0-a304-a3c2ba0493b0" />
      <xplan:planinhalt xlink:href="#GML_fd657926-28e8-49b5-9b5b-fe6ef646b6df" />
      <xplan:planinhalt xlink:href="#GML_4ff56b4b-da5e-4cba-8139-9dd736ba8e08" />
      <xplan:planinhalt xlink:href="#GML_0ce38652-a0fa-4c31-8200-5401767c60e3" />
      <xplan:planinhalt xlink:href="#GML_10510a15-3fe1-4b05-a58f-462703e8bdbc" />
      <xplan:planinhalt xlink:href="#GML_5a5e05c0-3a0d-439e-bdde-e8bfadaa9903" />
      <xplan:planinhalt xlink:href="#GML_602ed388-6ddf-4dc9-b720-bf2df0264d73" />
      <xplan:planinhalt xlink:href="#GML_a242d7ba-42a0-4c10-9fe0-b49558383350" />
      <xplan:planinhalt xlink:href="#GML_537d67d0-28c0-4fe0-b915-e6bfd54d09e0" />
      <xplan:planinhalt xlink:href="#GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34" />
      <xplan:planinhalt xlink:href="#GML_8898d662-079a-466c-a813-eb8d3f92ff39" />
      <xplan:planinhalt xlink:href="#GML_eb31558b-f294-44a4-bae2-ca3025816d1e" />
      <xplan:planinhalt xlink:href="#GML_618641e4-7c14-4a65-bf1e-6915c38b7d72" />
      <xplan:planinhalt xlink:href="#GML_e30e1b9b-89de-4c1c-becb-55482a747f45" />
      <xplan:planinhalt xlink:href="#GML_d4ad2294-92ad-424c-a65b-3648a83fa251" />
      <xplan:planinhalt xlink:href="#GML_99f42f84-23d5-4d4a-8a5d-985f5da248b1" />
      <xplan:planinhalt xlink:href="#GML_efabfe6b-27c1-47c7-b1db-238bf2c2ef24" />
      <xplan:planinhalt xlink:href="#GML_eb2c55e1-95ab-4047-b5ed-cef618975e6c" />
      <xplan:planinhalt xlink:href="#GML_8d116d2f-509e-4597-9c8f-5bb5527abe66" />
      <xplan:planinhalt xlink:href="#GML_dd4ccc4b-e64a-46c8-b2a5-198f95a7aaef" />
      <xplan:planinhalt xlink:href="#GML_981e1024-453b-451e-bc61-e27cbf632243" />
      <xplan:planinhalt xlink:href="#GML_7f5e7a57-6a7c-4188-875d-c5baa1c12e96" />
      <xplan:planinhalt xlink:href="#GML_5b29d92a-0faf-47d0-aa23-d11e5ffc595b" />
      <xplan:planinhalt xlink:href="#GML_9960dc2b-775f-4303-b6c4-0d02370ed3f2" />
      <xplan:planinhalt xlink:href="#GML_4e16896b-4dd9-4e43-910b-69b9bbeabb8d" />
      <xplan:planinhalt xlink:href="#GML_1d2e4da2-cf09-4aaf-a436-2cfe88838e11" />
      <xplan:planinhalt xlink:href="#GML_500e7a25-75f5-47f2-b627-fe1dbf21d94e" />
      <xplan:planinhalt xlink:href="#GML_154500ed-c813-4280-9ee4-2add13b3bc18" />
      <xplan:planinhalt xlink:href="#GML_9b9acc5d-c6f3-4095-9d22-8a8ab0e7ab47" />
      <xplan:planinhalt xlink:href="#GML_6774fa67-b62e-497c-a1f2-aef96c88d25a" />
      <xplan:planinhalt xlink:href="#GML_7a78a1b8-10df-4ce6-9195-d82aad5f29aa" />
      <xplan:planinhalt xlink:href="#GML_a0dd913d-29bc-40c9-a8a4-95627921add8" />
      <xplan:planinhalt xlink:href="#GML_9c407f91-f0b1-46f8-b698-6a3ca0e03b67" />
      <xplan:planinhalt xlink:href="#GML_58523c86-9eb6-4391-ab00-653893398968" />
      <xplan:planinhalt xlink:href="#GML_8797e25c-00e4-4eb2-b1b2-06aa6cb153b0" />
      <xplan:planinhalt xlink:href="#GML_a25f0efa-53cd-449f-80ac-0a0fa35554f4" />
      <xplan:planinhalt xlink:href="#GML_035f125a-831a-4ac7-aa90-c489c9b09ca5" />
      <xplan:planinhalt xlink:href="#GML_35884c9a-0f85-44f5-abfd-121b06c9db12" />
      <xplan:planinhalt xlink:href="#GML_70564618-c555-4230-96c4-cfe1e3532f4c" />
      <xplan:planinhalt xlink:href="#GML_13cbcfa8-a898-430d-a4f5-a94d19db9f12" />
      <xplan:planinhalt xlink:href="#GML_e54bc0d7-55d2-4eb0-8a43-0232ce0c2180" />
      <xplan:planinhalt xlink:href="#GML_86fc1baf-60ad-4b04-bc85-03a8527a9d8d" />
      <xplan:planinhalt xlink:href="#GML_b5281e4e-fb49-4fe8-9f96-14f62225cfab" />
      <xplan:planinhalt xlink:href="#GML_b8d046ab-a466-47aa-9d3e-1b82c14e7cb7" />
      <xplan:planinhalt xlink:href="#GML_757fd155-8cd4-4eca-90f6-5d67b198ed78" />
      <xplan:planinhalt xlink:href="#GML_37e5029c-a8d3-4d65-a033-b3d80ade0aaf" />
      <xplan:planinhalt xlink:href="#GML_11b47fe4-7623-4d1b-81f5-3bb1efddc270" />
      <xplan:planinhalt xlink:href="#GML_52e20261-5e96-4d5c-8f5d-ab5ec5ae9534" />
      <xplan:planinhalt xlink:href="#GML_9688f1ca-47dd-4296-9ddf-0e16dd96d4c0" />
      <xplan:planinhalt xlink:href="#GML_2c0030e0-10c1-4460-a36e-3a2b8196a166" />
      <xplan:planinhalt xlink:href="#GML_449c6f06-3dfb-474e-88bd-bb31382a877c" />
      <xplan:planinhalt xlink:href="#GML_33024b16-3df2-4165-a075-8015ffdd1199" />
      <xplan:planinhalt xlink:href="#GML_b5cfb3fd-df49-4c37-88e3-aac6f1e09b63" />
      <xplan:planinhalt xlink:href="#GML_69ba8667-931e-4abe-9569-d5d853848a7a" />
      <xplan:planinhalt xlink:href="#GML_fa891473-ad07-4bc8-a874-b117b9012a86" />
      <xplan:planinhalt xlink:href="#GML_1af9518d-3e51-455c-a973-f811393e2ea8" />
      <xplan:planinhalt xlink:href="#GML_4297ebe9-274d-47b9-9dcc-5336a7ac74e4" />
      <xplan:planinhalt xlink:href="#GML_3ef399b8-7c20-4036-acc4-0abe0858de7c" />
      <xplan:planinhalt xlink:href="#GML_845323e0-635b-45b5-b639-257674dbb5c2" />
      <xplan:planinhalt xlink:href="#GML_c5fb5d35-13f7-4b3b-93fb-e0a9860c8215" />
      <xplan:planinhalt xlink:href="#GML_61c03deb-c952-41a0-92ca-1aa011e4e3a6" />
      <xplan:planinhalt xlink:href="#GML_5b09fce9-7b05-498d-bdc1-6f2f78e7ef27" />
      <xplan:planinhalt xlink:href="#GML_de6f3e96-78df-42d5-8835-44826509ab59" />
      <xplan:planinhalt xlink:href="#GML_e7ff3715-6e8c-4a75-833f-776331abf102" />
      <xplan:planinhalt xlink:href="#GML_1c4745ae-5174-4584-8088-7e86856d290b" />
      <xplan:planinhalt xlink:href="#GML_90193278-e141-437b-9c52-d5678e60a315" />
      <xplan:planinhalt xlink:href="#GML_3b3ef4de-276a-48af-a120-f281a5552e17" />
      <xplan:planinhalt xlink:href="#GML_0aab0715-b872-4b50-a0b6-8aeebf51ca56" />
      <xplan:planinhalt xlink:href="#GML_03850a46-1088-4bf8-a11f-547e17ea7052" />
      <xplan:planinhalt xlink:href="#GML_193a5e28-7b26-4d97-920b-b136f6ad59c2" />
      <xplan:planinhalt xlink:href="#GML_a0709384-8458-4eb1-8d98-75e22687cc9c" />
      <xplan:planinhalt xlink:href="#GML_eaccfc25-af38-4ef2-aedf-84da7af2b00c" />
      <xplan:planinhalt xlink:href="#GML_0f5d3c76-97ab-4d95-afa4-90f8a621a166" />
      <xplan:planinhalt xlink:href="#GML_640786c9-4bbb-409f-a90a-7a53df0b97ec" />
      <xplan:planinhalt xlink:href="#GML_ac5e87f5-2c3e-4c55-a691-3133f8f95c23" />
      <xplan:planinhalt xlink:href="#GML_7f1642c7-8b93-44f3-bd0c-b4ed3ef11375" />
      <xplan:planinhalt xlink:href="#GML_f48acf6e-5290-413f-b15f-a25ee0582b83" />
      <xplan:planinhalt xlink:href="#GML_5d9af267-684b-4b16-9c06-56d952169f62" />
      <xplan:planinhalt xlink:href="#GML_2fc4caa5-7132-42a8-abd7-70db13017105" />
      <xplan:planinhalt xlink:href="#GML_5c642674-0122-497a-b4c6-d489e2914e70" />
      <xplan:planinhalt xlink:href="#GML_cdac95e0-2eda-4661-9806-58fc8b53257b" />
      <xplan:planinhalt xlink:href="#GML_fdd9d345-e676-4da0-840b-3c965f3f32d6" />
      <xplan:planinhalt xlink:href="#GML_4af95b70-4a69-4fc4-a550-e6d3bb40743c" />
      <xplan:planinhalt xlink:href="#GML_70ed3a05-95a8-4655-b881-9ea81c820448" />
      <xplan:planinhalt xlink:href="#GML_ecf2f3c5-dbb4-4133-84de-ffd946251b8c" />
      <xplan:planinhalt xlink:href="#GML_63205c3a-e599-4552-8c80-6f7af8c47c62" />
      <xplan:planinhalt xlink:href="#GML_be7ed20c-dfc1-460c-8c79-c7f5b3541a92" />
      <xplan:planinhalt xlink:href="#GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230" />
      <xplan:planinhalt xlink:href="#GML_a2abc023-c9ee-4ac7-878d-2e87d53f2fd5" />
      <xplan:planinhalt xlink:href="#GML_410ce780-790a-44d3-859d-de63c4cc4f83" />
      <xplan:planinhalt xlink:href="#GML_dbd0d811-79a1-4415-9aa0-7f0648a22c25" />
      <xplan:planinhalt xlink:href="#GML_9c9b2041-62ee-49f4-9d24-34c5312c801a" />
      <xplan:planinhalt xlink:href="#GML_0ce52e49-e37d-4d10-88cc-f648a7be5d80" />
      <xplan:planinhalt xlink:href="#GML_a2bfce95-4dc2-43b7-9837-c445164c1b24" />
      <xplan:planinhalt xlink:href="#GML_11d36424-1a5a-4b26-b423-2c81266be12d" />
      <xplan:planinhalt xlink:href="#GML_0d495f73-6ce9-4022-83e8-943fa77eb4bc" />
      <xplan:planinhalt xlink:href="#GML_15c40f54-f48f-47d3-a71b-4ee37ad3b370" />
      <xplan:planinhalt xlink:href="#GML_58ea50f9-4f0a-4383-9d1d-3260c9fca761" />
      <xplan:planinhalt xlink:href="#GML_db87cee1-192c-436d-a46e-14c48026de87" />
      <xplan:planinhalt xlink:href="#GML_9c66df6a-37d6-43ae-9926-6b8dfbec35a0" />
      <xplan:planinhalt xlink:href="#GML_32423208-6f19-464b-922d-da5f28ee95fc" />
      <xplan:planinhalt xlink:href="#GML_5285e6c7-408b-4997-bd5b-77c26cc06655" />
      <xplan:planinhalt xlink:href="#GML_6257dc81-b135-4c5a-840e-027810488711" />
      <xplan:planinhalt xlink:href="#GML_a14c02b4-e624-4ebc-80be-358a28d6884a" />
      <xplan:planinhalt xlink:href="#GML_2bd87674-56e8-4e88-a609-1ffc31d26707" />
      <xplan:planinhalt xlink:href="#GML_668c1a19-3b86-41b9-b044-1eb4c73069ad" />
      <xplan:planinhalt xlink:href="#GML_ca14e932-4e1b-4f7b-b978-2e9e7be47f0a" />
      <xplan:planinhalt xlink:href="#GML_91755c97-3300-46cf-9d30-e3664156df35" />
      <xplan:planinhalt xlink:href="#GML_e6567b66-6cbf-4f88-9238-374aebaaccd4" />
      <xplan:planinhalt xlink:href="#GML_1af950c4-47ae-46e8-a63f-c902e7a4639a" />
      <xplan:planinhalt xlink:href="#GML_3b318736-4cfb-473d-a702-a92c5ea3d41d" />
      <xplan:planinhalt xlink:href="#GML_f86f8524-4af4-4542-b742-0e7fbcbc5fb9" />
      <xplan:planinhalt xlink:href="#GML_98831a84-4c4c-46f7-9fcd-6ebae8166715" />
      <xplan:planinhalt xlink:href="#GML_305dc3da-3e3d-4168-895a-81ebb20b3a9b" />
      <xplan:planinhalt xlink:href="#GML_95b99c64-cb7c-4f0b-aa7a-a9bb794a5dbc" />
      <xplan:planinhalt xlink:href="#GML_583254d5-406b-4346-8ca2-8d41bd39ed52" />
      <xplan:planinhalt xlink:href="#GML_e4b5df11-6053-40a5-939c-2af5af7062be" />
      <xplan:planinhalt xlink:href="#GML_696d6517-a621-444d-8969-293af608be8e" />
      <xplan:planinhalt xlink:href="#GML_15cff1c8-2e8d-4ab6-b5be-da0d72f05f43" />
      <xplan:planinhalt xlink:href="#GML_0f6191d0-c35c-4618-bc0f-5d73ceb973b8" />
      <xplan:planinhalt xlink:href="#GML_77276cf3-8acc-4c38-b1d3-414b16a049f6" />
      <xplan:planinhalt xlink:href="#GML_14900c93-f8a4-4b7c-aeb8-11f0a9349df7" />
      <xplan:planinhalt xlink:href="#GML_f70db4c1-0164-40a0-a61f-896a69703980" />
      <xplan:planinhalt xlink:href="#GML_2f84dff8-fef6-4c7b-9a3f-d92b8287335f" />
      <xplan:planinhalt xlink:href="#GML_4ad531e0-a314-4dbb-b75e-d81df4d1c145" />
      <xplan:planinhalt xlink:href="#GML_f73ba642-f8a0-40e8-ba36-c74cf66ea8dc" />
      <xplan:planinhalt xlink:href="#GML_256d1c29-a554-4f7c-8999-40760385d80b" />
      <xplan:planinhalt xlink:href="#GML_ed45178a-7f4c-46af-99b7-b84bde4b80bc" />
      <xplan:planinhalt xlink:href="#GML_d8d0aea5-c6da-424b-a736-375d0b267194" />
      <xplan:planinhalt xlink:href="#GML_fa5531a6-12f4-4cdd-a541-a93a8c83d3ec" />
      <xplan:planinhalt xlink:href="#GML_25a05ebc-4379-4f60-9f3a-e254f1c575ea" />
      <xplan:planinhalt xlink:href="#GML_c77a8197-037b-4698-8e3a-dfb5d3b28698" />
      <xplan:planinhalt xlink:href="#GML_0b52a965-fd72-443a-a75d-a8d693891a28" />
      <xplan:planinhalt xlink:href="#GML_23e4443d-c5d3-4494-adf7-c6ddca9d3042" />
      <xplan:planinhalt xlink:href="#GML_d7462686-461a-4804-9de7-f676e6dcfa76" />
      <xplan:planinhalt xlink:href="#GML_2d4640c5-adbd-40ad-9f97-c360ee8192d5" />
      <xplan:planinhalt xlink:href="#GML_9f80e9c6-1681-44c6-b9f8-62f030600dda" />
      <xplan:planinhalt xlink:href="#GML_78ecd6a5-0ece-493e-af8f-189bdf8b6976" />
      <xplan:planinhalt xlink:href="#GML_2d155575-31b1-4e01-a996-e1752c798084" />
      <xplan:planinhalt xlink:href="#GML_153ef48b-a709-476d-bc85-71d1f0d05770" />
      <xplan:planinhalt xlink:href="#GML_17440e1c-61d7-4f42-8d82-db8f3e966d64" />
      <xplan:planinhalt xlink:href="#GML_5f1742df-b5af-439a-b518-a6487bb937a5" />
      <xplan:planinhalt xlink:href="#GML_b87c872f-56da-4b8c-947a-f712ff4510e1" />
      <xplan:planinhalt xlink:href="#GML_abdb3126-8e67-4e77-8c23-52261d30de29" />
      <xplan:planinhalt xlink:href="#GML_46a964e3-b714-4d26-a891-ebda886979cf" />
      <xplan:planinhalt xlink:href="#GML_0524efaf-b3e5-40f2-83bf-4d8563133620" />
      <xplan:planinhalt xlink:href="#GML_8ebb5c68-7fe4-4d1a-996e-ccf5feaeaf37" />
      <xplan:planinhalt xlink:href="#GML_b52de912-5178-496e-a3ce-7e32f40ce463" />
      <xplan:planinhalt xlink:href="#GML_03121dc5-dfcb-4640-8bbc-5dae50c40b6f" />
      <xplan:planinhalt xlink:href="#GML_b4f9f49a-0a3e-4837-bc59-fc9802bd6bd6" />
      <xplan:planinhalt xlink:href="#GML_146890fb-71f0-4b47-a800-0c9b02852879" />
      <xplan:planinhalt xlink:href="#GML_ef2793da-5ac0-49cb-82e2-67bd770492e9" />
      <xplan:planinhalt xlink:href="#GML_a2ef3c09-2d64-41a0-9f7c-21ff7ca6c256" />
      <xplan:planinhalt xlink:href="#GML_b8040098-602f-4e55-a301-f54ebbd5527c" />
      <xplan:planinhalt xlink:href="#GML_927927eb-407c-4b5f-a627-05059910272d" />
      <xplan:planinhalt xlink:href="#GML_d643f79a-03c2-4a87-b3fa-8abee779f018" />
      <xplan:planinhalt xlink:href="#GML_b130a89a-38db-488e-a2b7-c8ad4604c8b6" />
      <xplan:planinhalt xlink:href="#GML_6cdd76d0-1a7f-4594-b3aa-a5a11fa9b9da" />
      <xplan:planinhalt xlink:href="#GML_5e18029a-40c4-4a02-b339-273f8be7e02e" />
      <xplan:planinhalt xlink:href="#GML_e6726f89-3240-44e3-8457-24f9e7cd261b" />
      <xplan:planinhalt xlink:href="#GML_c0aa7384-9d95-4121-b5a8-ce48ff595148" />
      <xplan:praesentationsobjekt xlink:href="#GML_7a9ad80f-d266-4ca2-a60e-ad58ec9f3499" />
      <xplan:praesentationsobjekt xlink:href="#GML_b94ac3dc-3362-4e87-9e00-477cc5718c24" />
      <xplan:praesentationsobjekt xlink:href="#GML_cc8649ef-5a5e-449c-b630-8a3265c27fef" />
      <xplan:praesentationsobjekt xlink:href="#GML_df0c88a7-87d3-44c4-bfa9-a8697e35f89b" />
      <xplan:praesentationsobjekt xlink:href="#GML_38ee1065-4767-4030-8485-91c7303c4525" />
      <xplan:praesentationsobjekt xlink:href="#GML_0722af4b-5055-46f0-86ff-a1aa9a063932" />
      <xplan:praesentationsobjekt xlink:href="#GML_c2b1fe14-d223-4cdb-8d3d-03deb21f43d7" />
      <xplan:praesentationsobjekt xlink:href="#GML_6d419faf-4d8e-4a72-93fc-e53782b8b2f5" />
      <xplan:praesentationsobjekt xlink:href="#GML_839d672d-dcdd-4a08-84c9-c4859be6b394" />
      <xplan:praesentationsobjekt xlink:href="#GML_3a02f33d-3c42-4150-8fae-0b5b5a521061" />
      <xplan:praesentationsobjekt xlink:href="#GML_e241e8e4-8b12-4574-a61d-a8ab8dc7b739" />
      <xplan:praesentationsobjekt xlink:href="#GML_061681e8-debb-42d8-a05e-52ae3a583be5" />
      <xplan:praesentationsobjekt xlink:href="#GML_b38f687d-0607-4268-a8bf-9621055a8ad3" />
      <xplan:praesentationsobjekt xlink:href="#GML_c7a392b3-e5bc-42d3-bd3f-baa010592ffc" />
      <xplan:praesentationsobjekt xlink:href="#GML_15ea1be7-2ae9-4ce3-85c2-34d5c55f67d9" />
      <xplan:praesentationsobjekt xlink:href="#GML_0cd4f1d9-2e4c-4d89-ad70-f6b7e8a0157b" />
      <xplan:praesentationsobjekt xlink:href="#GML_ff4c6ea4-f7cd-4dcd-a244-6524759e83e1" />
      <xplan:praesentationsobjekt xlink:href="#GML_60179d2f-11cd-4afc-ab84-1ae7658b1aa6" />
      <xplan:praesentationsobjekt xlink:href="#GML_18d9cfab-f977-4a41-9f0f-53d0c44f4aeb" />
      <xplan:praesentationsobjekt xlink:href="#GML_405c6016-bdb8-45d5-a0fd-a1795f9b1dac" />
      <xplan:praesentationsobjekt xlink:href="#GML_704e61f5-6692-4565-aeee-4dacc3271ea5" />
      <xplan:praesentationsobjekt xlink:href="#GML_d6edbcef-8212-4a08-ada5-c43437074ad6" />
      <xplan:praesentationsobjekt xlink:href="#GML_5f6fd4af-6bb1-438c-b91e-e3e244a35fb6" />
      <xplan:praesentationsobjekt xlink:href="#GML_4a14944e-332a-4a77-b9d4-be9d45d9ec7b" />
      <xplan:praesentationsobjekt xlink:href="#GML_a8cf0ab5-d0ac-4b74-8ef7-097174e0b3a0" />
      <xplan:praesentationsobjekt xlink:href="#GML_29d54638-4843-4e20-b72c-40d123e6ffa8" />
      <xplan:praesentationsobjekt xlink:href="#GML_45f5f309-20a2-40e1-924e-24c281c9c493" />
      <xplan:praesentationsobjekt xlink:href="#GML_fd5ed2d4-4a70-4f02-9cb2-485e4fa6dd79" />
      <xplan:praesentationsobjekt xlink:href="#GML_4bd8031d-89f4-4e22-a74b-15c30e65adfa" />
      <xplan:praesentationsobjekt xlink:href="#GML_bc891081-5c85-4443-9c69-f5a26ad281a7" />
      <xplan:praesentationsobjekt xlink:href="#GML_0a3de316-3ef3-438c-a34d-52fab492d2ad" />
      <xplan:praesentationsobjekt xlink:href="#GML_48aeee0c-3768-4859-ad0d-a005916f217f" />
      <xplan:praesentationsobjekt xlink:href="#GML_43afaccc-b92e-40c8-9298-9fe813d17625" />
      <xplan:praesentationsobjekt xlink:href="#GML_cb6eb28a-be08-405a-940c-e2a01145b1a3" />
      <xplan:praesentationsobjekt xlink:href="#GML_d732713d-6c1b-4298-abe9-1590e7bc5f08" />
      <xplan:praesentationsobjekt xlink:href="#GML_ae2b412b-6c1f-4170-858c-be67567b2044" />
      <xplan:praesentationsobjekt xlink:href="#GML_5b340fcd-e9b6-4856-81d2-262498dfc4ef" />
      <xplan:praesentationsobjekt xlink:href="#GML_96430730-a228-4abd-b09e-259849bae9e5" />
      <xplan:praesentationsobjekt xlink:href="#GML_31db0adc-dc7b-44b3-8450-e1e324c174e1" />
      <xplan:praesentationsobjekt xlink:href="#GML_78ef0ebd-ea2d-441f-968f-e544d9e1c9dc" />
      <xplan:praesentationsobjekt xlink:href="#GML_806b3e8f-060f-4895-95ad-29339633d780" />
      <xplan:praesentationsobjekt xlink:href="#GML_25e73a2a-f476-4eb2-93d3-d85c66f0d3ed" />
      <xplan:praesentationsobjekt xlink:href="#GML_9a6be2c2-4816-4489-8793-a9c427660f5c" />
      <xplan:praesentationsobjekt xlink:href="#GML_2b92f5ad-86dc-469b-9cc5-2a8f63ad0f14" />
      <xplan:praesentationsobjekt xlink:href="#GML_ff5076e2-3928-4f22-9a11-3639f06cfb67" />
      <xplan:praesentationsobjekt xlink:href="#GML_e612b54e-9346-4ff2-938e-66d45dd99129" />
      <xplan:praesentationsobjekt xlink:href="#GML_686531c8-db4e-4cd1-809b-ae13980992a1" />
      <xplan:praesentationsobjekt xlink:href="#GML_bc5d5385-2b17-4047-8d7d-a092c9ec3f21" />
      <xplan:praesentationsobjekt xlink:href="#GML_cf6ce712-0907-42bb-8e53-d511f4843b8a" />
      <xplan:praesentationsobjekt xlink:href="#GML_dbdbfd4f-fa04-4ee4-8d1b-3a939bbb9b25" />
      <xplan:praesentationsobjekt xlink:href="#GML_80935cd5-1b2c-48a4-9240-8d56a4603d07" />
      <xplan:praesentationsobjekt xlink:href="#GML_29dc4c70-596c-4b31-89b0-2031949f0a81" />
      <xplan:praesentationsobjekt xlink:href="#GML_a59ef4b5-d171-4862-a8ff-cd07d348d33d" />
      <xplan:praesentationsobjekt xlink:href="#GML_6b00776a-2bd0-4725-8535-b8eee96e2708" />
      <xplan:praesentationsobjekt xlink:href="#GML_fa813021-0be7-41dc-8363-da5e5de709ce" />
      <xplan:praesentationsobjekt xlink:href="#GML_db233e36-e8df-44df-b861-3f37c8a798b1" />
      <xplan:praesentationsobjekt xlink:href="#GML_5be51555-9d12-441c-b545-5610a3d4c443" />
      <xplan:praesentationsobjekt xlink:href="#GML_ef5b831c-4ea9-40fc-9094-246688ac8f92" />
      <xplan:praesentationsobjekt xlink:href="#GML_bb972aa4-f953-4c06-93d7-f01ea9280de1" />
      <xplan:praesentationsobjekt xlink:href="#GML_8be3b99b-c2d9-4a58-877e-b71d4db77638" />
      <xplan:praesentationsobjekt xlink:href="#GML_80f04a76-1034-4dd2-9278-2f524cef9995" />
      <xplan:praesentationsobjekt xlink:href="#GML_ab75fa47-0243-4d15-9ec4-f095991c575b" />
      <xplan:praesentationsobjekt xlink:href="#GML_0fa5f074-3ac5-4f0f-b68c-7cd7076ca862" />
      <xplan:praesentationsobjekt xlink:href="#GML_4b53c49a-63ef-4de6-a1e4-84874d90692e" />
      <xplan:praesentationsobjekt xlink:href="#GML_b3268d0a-e7af-4eec-9f3b-07d7e92391cf" />
      <xplan:praesentationsobjekt xlink:href="#GML_06c0ec67-a008-4b9a-8f55-9943b51ce168" />
      <xplan:praesentationsobjekt xlink:href="#GML_1747eb3f-6ea0-4321-a43d-718574e7758f" />
      <xplan:praesentationsobjekt xlink:href="#GML_7fe47f0f-4340-4aee-9ea5-7c1950a268d3" />
      <xplan:praesentationsobjekt xlink:href="#GML_fb5b0473-1a22-4340-b746-7fb5f4fc3973" />    
      <xplan:praesentationsobjekt xlink:href="#GML_0068f757-a3ff-427d-9839-b656aa85cefd" />
      <xplan:praesentationsobjekt xlink:href="#GML_8e9e567b-ebb5-46d7-b646-2ebb4b511ac4" />
      <xplan:praesentationsobjekt xlink:href="#GML_2bf9a728-de04-4b5a-b1c6-2fb994b677ae" />
      <xplan:praesentationsobjekt xlink:href="#GML_c18f0601-089b-4078-a2a7-9257543b1b4b" />
      <xplan:praesentationsobjekt xlink:href="#GML_bfb48166-05bc-48af-9994-11ef26020460" />
      <xplan:praesentationsobjekt xlink:href="#GML_af1e6a12-7e82-4660-b922-fb3d0f28e741" />
      <xplan:praesentationsobjekt xlink:href="#GML_6a544600-7d53-4f60-94c0-d2579125adcd" />
      <xplan:praesentationsobjekt xlink:href="#GML_1844faea-06c5-496a-9290-c7a5d6ace885" />
      <xplan:praesentationsobjekt xlink:href="#GML_4bbbbac4-6a65-4039-a8cc-a8844004c3ce" />
      <xplan:praesentationsobjekt xlink:href="#GML_2927f2d3-73dd-4331-9a35-d39dd2bcab39" />
      <xplan:praesentationsobjekt xlink:href="#GML_1a22d80d-006f-4e41-b301-68c295a4ee61" />
      <xplan:praesentationsobjekt xlink:href="#GML_a60af9b2-0fae-4b06-b82f-8c0a89384d1c" />
      <xplan:praesentationsobjekt xlink:href="#GML_729cf43f-e356-4e0f-9cf7-ef88fb8bece1" />
      <xplan:praesentationsobjekt xlink:href="#GML_aa25e2a1-d386-40b1-a79b-405bf2a8cce9" />
      <xplan:praesentationsobjekt xlink:href="#GML_288c4114-5726-4343-b414-a6cb246b99a0" />
      <xplan:praesentationsobjekt xlink:href="#GML_d0b354ca-832b-427b-9fd0-432e65db1b8a" />
      <xplan:praesentationsobjekt xlink:href="#GML_39c00556-6f68-4373-a1fa-4afca30e8044" />
      <xplan:praesentationsobjekt xlink:href="#GML_140093af-4430-4f5c-abae-f299cba3b63b" />
      <xplan:praesentationsobjekt xlink:href="#GML_caf44381-5def-43a4-a85f-2d586a6cda01" />
      <xplan:praesentationsobjekt xlink:href="#GML_b23b7c5f-aafd-4643-8145-10406d444814" />
      <xplan:praesentationsobjekt xlink:href="#GML_b0d4b2e3-f164-47f0-b8f8-0bdc6476515f" />
      <xplan:praesentationsobjekt xlink:href="#GML_4ad10e2c-083e-440f-84a3-9ddc5c488a2e" />
      <xplan:praesentationsobjekt xlink:href="#GML_eddcc363-94c3-41ce-a417-7796813fa692" />
      <xplan:praesentationsobjekt xlink:href="#GML_124975c1-347c-4cdd-a946-7091b5cf6b34" />
      <xplan:praesentationsobjekt xlink:href="#GML_109b304e-43aa-45ef-bf0b-e1fa61a268c3" />
      <xplan:praesentationsobjekt xlink:href="#GML_500e5000-78ec-490a-80ce-e3b45c54c319" />
      <xplan:praesentationsobjekt xlink:href="#GML_ee977ef8-de15-4c7b-8ead-39fc4d04370b" />
      <xplan:praesentationsobjekt xlink:href="#GML_82a414e3-2862-47bb-b0a5-40d584a94abc" />
      <xplan:praesentationsobjekt xlink:href="#GML_2ebaff1b-6cdd-4257-9dbe-bae2e6f8a01f" />
      <xplan:praesentationsobjekt xlink:href="#GML_67dc2e78-b355-42e0-8215-77b862e7c27a" />
      <xplan:praesentationsobjekt xlink:href="#GML_71faf3d6-41c7-4bce-98bd-b05b7976ac67" />
      <xplan:praesentationsobjekt xlink:href="#GML_1cdd2da6-30b7-450d-ab4a-5af1f2fa298e" />
      <xplan:praesentationsobjekt xlink:href="#GML_0d7e3dda-291e-4f00-8de9-6265e62020aa" />
      <xplan:praesentationsobjekt xlink:href="#GML_daf967a8-4877-4b3d-b67c-44c8dad66026" />
      <xplan:praesentationsobjekt xlink:href="#GML_927889a0-067a-45ff-b560-61b58ca331c2" />
      <xplan:praesentationsobjekt xlink:href="#GML_f2f1fd49-5c43-4e50-b477-8fe72f7ee72a" />
      <xplan:praesentationsobjekt xlink:href="#GML_eb8103b3-4021-4c87-aa5f-440c8663569b" />
      <xplan:praesentationsobjekt xlink:href="#GML_9a6e30e0-5534-44c1-947a-ea7930f6f89b" />
      <xplan:praesentationsobjekt xlink:href="#GML_5b85dce4-48c9-4194-b89d-8c3bebf546e3" />
      <xplan:praesentationsobjekt xlink:href="#GML_40525bd4-8008-4441-bfe7-42917becd2d6" />
      <xplan:praesentationsobjekt xlink:href="#GML_543955f5-5c5f-416a-8de8-711003dd0736" />
      <xplan:praesentationsobjekt xlink:href="#GML_6ce2f8c1-77ef-46a0-97ec-1e22c08e6ebe" />
      <xplan:praesentationsobjekt xlink:href="#GML_524ab7c4-d4e7-43c4-8893-303cffbf51ce" />
      <xplan:praesentationsobjekt xlink:href="#GML_7a0d3830-06e5-4eef-a1a2-da0878e7cd43" />
      <xplan:praesentationsobjekt xlink:href="#GML_c7ddcd14-32cd-4d5b-bca3-43cd64651433" />
      <xplan:praesentationsobjekt xlink:href="#GML_58f3600e-e8b8-4f07-8806-d4378df0f9bf" />
      <xplan:praesentationsobjekt xlink:href="#GML_6889599c-38d8-4a50-b12f-b280898aef3f" />
      <xplan:praesentationsobjekt xlink:href="#GML_0aeddea9-203f-4693-88dc-635260105501" />
      <xplan:praesentationsobjekt xlink:href="#GML_15c980ea-21cb-4765-85f3-30e46f0db717" />
      <xplan:praesentationsobjekt xlink:href="#GML_6d84fc57-f194-46cd-b2f0-93f248d3e7d4" />
      <xplan:praesentationsobjekt xlink:href="#GML_614a7efe-b0a8-496a-8d9d-84c83be1322e" />
      <xplan:praesentationsobjekt xlink:href="#GML_5a4bed7f-7926-4a75-a550-6f35f8b83f1d" />
      <xplan:praesentationsobjekt xlink:href="#GML_d536e6c1-b5f9-4bef-acde-574bb56b9d30" />
      <xplan:praesentationsobjekt xlink:href="#GML_90ffc140-b75b-43ca-b3e2-99e4c6813265" />
      <xplan:praesentationsobjekt xlink:href="#GML_db92ae6b-8c00-4f15-b897-bfae3abd7bca" />
      <xplan:praesentationsobjekt xlink:href="#GML_eb7ee37f-4fbe-4663-944d-b0aa1cb91d77" />
      <xplan:praesentationsobjekt xlink:href="#GML_3c72d4ab-e5ca-4e0b-b19f-a08b2987f4d2" />
      <xplan:praesentationsobjekt xlink:href="#GML_d1ab6b20-cef6-4e3e-ad1d-c732e2d71680" />
      <xplan:praesentationsobjekt xlink:href="#GML_48fbfcdb-21d8-4579-a616-ea3166363bd1" />
      <xplan:praesentationsobjekt xlink:href="#GML_c43ab2ed-5bed-4051-b67f-5768e4d51f42" />
      <xplan:praesentationsobjekt xlink:href="#GML_9d2c1bc9-d41a-4ac2-b5d6-3cc95c2b3dd2" />
      <xplan:praesentationsobjekt xlink:href="#GML_23e92186-a7c8-45cd-ac41-8f8cb24ec00b" />
      <xplan:praesentationsobjekt xlink:href="#GML_e9c4a430-9fc5-42c1-a786-3c47233b412a" />
      <xplan:praesentationsobjekt xlink:href="#GML_19d26cd7-dc12-4e88-af48-a1e22579a229" />
      <xplan:praesentationsobjekt xlink:href="#GML_ce5f1622-46d2-419e-9a98-635cafc8eaea" />
      <xplan:praesentationsobjekt xlink:href="#GML_18eabc5a-0fdb-484d-a88f-b4ae016fde30" />
      <xplan:praesentationsobjekt xlink:href="#GML_ad508406-e9b2-4889-9199-0673975bd12a" />
      <xplan:praesentationsobjekt xlink:href="#GML_b88a5e5e-703d-4ec2-ba0a-ee854f9f59da" />
      <xplan:praesentationsobjekt xlink:href="#GML_60923779-3cd5-4725-80f3-ca233d9e542d" />
      <xplan:praesentationsobjekt xlink:href="#GML_deb2b7ef-0c45-4773-bde6-d6b9666eaceb" />
      <xplan:praesentationsobjekt xlink:href="#Gml_59C98B99-9EC5-4AAD-8113-1D3AB96C25BE" />
      <xplan:praesentationsobjekt xlink:href="#Gml_0C250944-2209-47D0-95A0-52FB69F55371" />
      <xplan:praesentationsobjekt xlink:href="#Gml_1EC65BFB-F3E6-487F-A8C4-E80F7FB8A6B4" />
      <xplan:praesentationsobjekt xlink:href="#Gml_517AD821-E3DA-45EB-8E93-35FC156ADF54" />
      <xplan:praesentationsobjekt xlink:href="#Gml_7D1BCD74-9CED-4846-8035-88CAE929D11E" />
      <xplan:praesentationsobjekt xlink:href="#Gml_FF90141C-327E-4477-BCC3-F0924685F026" />
      <xplan:praesentationsobjekt xlink:href="#Gml_7EA60D7E-8804-4C4A-A356-C028B4978B55" />
      <xplan:praesentationsobjekt xlink:href="#Gml_87A85004-6371-425C-A304-A4A893AC0F40" />
      <xplan:praesentationsobjekt xlink:href="#Gml_F76401B7-60CD-4A0B-81B2-15F9A2AECB0C" />
      <xplan:praesentationsobjekt xlink:href="#Gml_83142A8D-47A9-436E-8D37-7E18C2D547CC" />
      <xplan:praesentationsobjekt xlink:href="#Gml_424654DD-B467-4E99-BBD1-12534871136D" />
      <xplan:praesentationsobjekt xlink:href="#Gml_63BFE4F5-01AC-4DF6-845E-7661CBDBF50C" />
      <xplan:praesentationsobjekt xlink:href="#Gml_FDCD2D62-F84F-43BB-BC98-E1FC5B1BA7D3" />
      <xplan:praesentationsobjekt xlink:href="#Gml_CD54191D-1378-4B88-8607-210D44A08E4F" />
      <xplan:praesentationsobjekt xlink:href="#Gml_F094093A-5E0E-4063-B8A5-913E91C9731B" />
      <xplan:praesentationsobjekt xlink:href="#Gml_EF610229-0F92-401F-AB98-23526A9F15B0" />
      <xplan:praesentationsobjekt xlink:href="#Gml_A0B47BE4-7C8A-4AFD-8BF5-DFCCDBD5E22C" />
      <xplan:praesentationsobjekt xlink:href="#Gml_8EA078BB-3149-47E7-A487-87B982B43506" />
      <xplan:praesentationsobjekt xlink:href="#Gml_45940F8C-EC1F-491C-8BE2-331F0619142A" />
      <xplan:praesentationsobjekt xlink:href="#Gml_E6EE95D3-7B20-41E1-8AB1-060C221E693F" />
      <xplan:praesentationsobjekt xlink:href="#Gml_F057FEF3-780F-40A3-B093-1E82D852137B" />
      <xplan:praesentationsobjekt xlink:href="#Gml_947876A1-2C6C-43F9-B00B-E24CD83259B1" />
      <xplan:praesentationsobjekt xlink:href="#Gml_1C715386-85EF-44E3-99F7-0665B749B6C5" />
      <xplan:praesentationsobjekt xlink:href="#Gml_56B95EB3-CEE2-4E38-9ED1-FBF3C2DA6427" />
      <xplan:praesentationsobjekt xlink:href="#Gml_6DF1AA82-043A-4E77-9B5C-8DD328D59A16" />
      <xplan:praesentationsobjekt xlink:href="#Gml_AA5620E3-4AB3-46B0-99A3-009D6EE86FB5" />
      <xplan:praesentationsobjekt xlink:href="#Gml_F43A2B52-4442-445A-BA56-419C46DABC6E" />
      <xplan:praesentationsobjekt xlink:href="#Gml_2339E194-0F76-4F7E-AE83-8EACA56D0FD6" />
      <xplan:praesentationsobjekt xlink:href="#Gml_0163D672-E078-4524-874F-B73E68B230DC" />
      <xplan:praesentationsobjekt xlink:href="#Gml_C7FEFED8-0310-4054-982A-5360C7C7D194" />
      <xplan:praesentationsobjekt xlink:href="#Gml_2BE107FE-C0AA-4D7B-91B7-91AAFA3C474E" />
      <xplan:praesentationsobjekt xlink:href="#Gml_5C5481A0-6B77-4BBD-81FD-AAA1F6200BD8" />
      <xplan:praesentationsobjekt xlink:href="#Gml_263B7E60-BBAB-4FAD-8017-4C1C77845367" />
      <xplan:praesentationsobjekt xlink:href="#Gml_07919210-4369-4DB8-8A2B-B5B6E489D557" />
      <xplan:praesentationsobjekt xlink:href="#Gml_96900436-A4FF-4641-822F-244345AC2EA2" />
      <xplan:praesentationsobjekt xlink:href="#Gml_8471A0D0-8C82-479C-A8FA-39A3C9D03962" />
      <xplan:praesentationsobjekt xlink:href="#Gml_B012D118-AB54-4C22-AAD2-B50D7D9DC674" />
      <xplan:praesentationsobjekt xlink:href="#Gml_844E9879-692A-4220-BC9B-6A787829C224" />
      <xplan:praesentationsobjekt xlink:href="#Gml_15ED2463-1D1F-4895-9B16-AD856959D2E1" />
      <xplan:praesentationsobjekt xlink:href="#Gml_16EA18D6-7667-410E-9552-6A02951F0497" />
      <xplan:praesentationsobjekt xlink:href="#Gml_BAC2F703-D7F0-4743-A5D2-AEB0B1DACC0F" />
      <xplan:praesentationsobjekt xlink:href="#Gml_479FEDB1-78EA-4831-9DE7-7EB25C1A1246" />
      <xplan:praesentationsobjekt xlink:href="#Gml_098DA8FD-00FF-40E9-B91E-3C85E9363BEF" />
      <xplan:praesentationsobjekt xlink:href="#Gml_C4148D19-6EB4-4BE6-BD93-B81DB0E376D0" />
      <xplan:praesentationsobjekt xlink:href="#Gml_453136FE-A592-4CDE-A151-F84E37EABC15" />
      <xplan:praesentationsobjekt xlink:href="#Gml_C32A3807-B3DF-4B45-8F19-61108319C391" />
      <xplan:praesentationsobjekt xlink:href="#Gml_5BCFE120-407E-4439-9B5B-201EFB858C1A" />
      <xplan:praesentationsobjekt xlink:href="#Gml_DE21D296-7E88-4277-869B-C08189B88609" />
      <xplan:praesentationsobjekt xlink:href="#Gml_EC7A31DB-7E40-4C31-9175-C85183B15628" />
      <xplan:praesentationsobjekt xlink:href="#Gml_4F0AB60A-5562-4D3C-99B6-4770305A1FF3" />
      <xplan:praesentationsobjekt xlink:href="#Gml_1111027D-1EF7-48EA-AAC0-7000B5D50A23" />
      <xplan:praesentationsobjekt xlink:href="#Gml_61ADE8AE-E024-4601-98FC-8743BE696C3F" />
      <xplan:praesentationsobjekt xlink:href="#Gml_E9E5FFD4-20CE-481D-BB7A-CB1903B5F671" />
      <xplan:praesentationsobjekt xlink:href="#Gml_3CCFF559-C66A-409E-AE2B-574B161112BD" />
      <xplan:praesentationsobjekt xlink:href="#Gml_EA60A4AB-F558-4E8D-B22E-DD7754BF35FF" />
      <xplan:praesentationsobjekt xlink:href="#Gml_A1979071-3E4C-4722-838A-0F180956F880" />
      <xplan:praesentationsobjekt xlink:href="#Gml_A9354689-BA25-45CD-B4BB-531A9CB47644" />
      <xplan:praesentationsobjekt xlink:href="#Gml_BBADD0D5-AAC9-49D6-925F-B1840ACF76EC" />
      <xplan:praesentationsobjekt xlink:href="#Gml_CA84EBEA-932F-401F-8790-FAF79D0AEAD5" />
      <xplan:praesentationsobjekt xlink:href="#Gml_4A626CE9-D662-4B6C-8CB6-D0DB8859D836" />
      <xplan:praesentationsobjekt xlink:href="#Gml_1E99076D-213D-448D-8E8D-98F213F47A3D" />
      <xplan:praesentationsobjekt xlink:href="#Gml_EE6F6638-280B-4DD9-A134-09F202E6A02C" />
      <xplan:praesentationsobjekt xlink:href="#Gml_95EF6423-F7A2-467A-AD0F-A589ECC07F10" />
      <xplan:praesentationsobjekt xlink:href="#Gml_CB9D1DB9-74B2-4783-AF03-DFC4732D8AB0" />
      <xplan:praesentationsobjekt xlink:href="#Gml_563D32CC-3153-4396-84DD-0AE962ADF652" />
      <xplan:praesentationsobjekt xlink:href="#Gml_D6467550-4FAD-4242-AB6B-8FD767A24AA2" />
      <xplan:praesentationsobjekt xlink:href="#Gml_7907FB1E-D873-484D-931D-AA84DACDD428" />
      <xplan:praesentationsobjekt xlink:href="#Gml_92128F9E-EC87-40ED-A4C1-7C155F99ECA9" />
      <xplan:praesentationsobjekt xlink:href="#Gml_2D00037E-777E-4FDA-BAF9-04BA4E0A5298" />
      <xplan:praesentationsobjekt xlink:href="#Gml_08935D60-83A6-430F-BD1C-A7BCB47180AD" />
      <xplan:praesentationsobjekt xlink:href="#Gml_88B5A80D-9099-4B77-AC0B-9CCC29E6ED11" />
      <xplan:praesentationsobjekt xlink:href="#Gml_CE455CFB-F0C8-48F4-A339-A529D3468CA7" />
      <xplan:praesentationsobjekt xlink:href="#Gml_9661675F-E10B-40D7-9E2D-515899351025" />
      <xplan:praesentationsobjekt xlink:href="#Gml_F21BA8CF-449C-4B29-9AE3-C53EE109B47A" />
      <xplan:praesentationsobjekt xlink:href="#Gml_D72AECB0-ECE7-4612-984C-06B8EABD3252" />
      <xplan:praesentationsobjekt xlink:href="#Gml_89D5C38F-0342-4603-B77F-AAE4434597E8" />
      <xplan:praesentationsobjekt xlink:href="#Gml_0FCBFA6A-6221-4E02-9D06-D412D8596D22" />
      <xplan:praesentationsobjekt xlink:href="#Gml_9CBB84DA-1CED-4BDA-8287-4C25023D3B20" />
      <xplan:praesentationsobjekt xlink:href="#Gml_85D31676-5405-4B55-B099-CEB5E6F3F83D" />
      <xplan:praesentationsobjekt xlink:href="#Gml_0A3D9FB3-1246-4863-9936-ED2A115B8D9D" />
      <xplan:praesentationsobjekt xlink:href="#Gml_1B982A82-6C0A-4E7E-88CD-B579DD4ECCF3" />
      <xplan:praesentationsobjekt xlink:href="#Gml_A3D538EB-70D2-4108-970F-F3BD2DC54CA4" />
      <xplan:praesentationsobjekt xlink:href="#Gml_7B4B56B4-86A5-4C83-9136-D2C78D950539" />      
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:gehoertZuPlan xlink:href="#GML_4e02cb5a-77f2-4329-a668-2af01f3db0c0" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_9270e882-25b2-4dd2-8f3c-ce2d7c0541c7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553081.048 5935001.594</gml:lowerCorner>
          <gml:upperCorner>553101.477 5935025.162</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:text>Sonstige Abgrenzung</xplan:text>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_3F19C589-DAEC-4D98-908B-8EF10CB1AE29" srsName="EPSG:25832">
          <gml:posList>553101.477 5935025.162 553094.506 5935016.995 553084.786 5935005.613 
553081.048 5935001.594 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>9999</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_279c6a95-6211-4748-bb72-2edab8a0baca">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.309 5934922.907</gml:lowerCorner>
          <gml:upperCorner>553153.766 5934931.949</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_4bbbbac4-6a65-4039-a8cc-a8844004c3ce" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_CD875220-19FB-49F9-9C0F-27D09B759B29" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553153.766 5934923.462 553153.294 5934931.949 553143.309 5934931.394 
553143.781 5934922.907 553153.766 5934923.462 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_4bbbbac4-6a65-4039-a8cc-a8844004c3ce">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553151.513 5934928.566</gml:lowerCorner>
          <gml:upperCorner>553151.513 5934928.566</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_279c6a95-6211-4748-bb72-2edab8a0baca" />
      <xplan:position>
        <gml:Point gml:id="Gml_AF91F57A-A113-4979-8AAC-F4D07CE185D7" srsName="EPSG:25832">
          <gml:pos>553151.513 5934928.566</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_4b97d1c5-89ef-4fe9-a1f8-94a5b06f3144">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553174.627 5934864.566</gml:lowerCorner>
          <gml:upperCorner>553193.227 5934883.814</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_BBADD0D5-AAC9-49D6-925F-B1840ACF76EC" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_CA84EBEA-932F-401F-8790-FAF79D0AEAD5" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_4A626CE9-D662-4B6C-8CB6-D0DB8859D836" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_1E99076D-213D-448D-8E8D-98F213F47A3D" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0F2E1B6C-BFB7-4332-9D95-D1665FD1D4A1" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_5D4F2BAA-6065-4DA2-AF9E-9FE53E359F02" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553193.227 5934865.341 553192.743060412 5934866.49586231 553192.221 5934867.634 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_BCB54F83-6B8D-45F9-A4DD-2AFC16FF6BAC" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553192.221 5934867.634 553188.92910789 5934874.81577452 553185.402 5934881.885 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_955EEE0F-F4B3-4D2B-8658-EEA193AF4F32" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553185.402 5934881.885 553183.625 5934883.814 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_34087C56-28EA-4B1C-B859-377EA18B78AF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.625 5934883.814 553174.627 5934878.962 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F904BE04-90D9-4D1C-BD69-6F315E410968" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553174.627 5934878.962 553178.731 5934871.214 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6B23EDB0-B7F5-420D-8A1C-C6364F4D3548" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553178.731 5934871.214 553182.732 5934866.536 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_87DAAEB9-EF35-449A-B633-BA5DDE99DEC2" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553182.732 5934866.536 553184.207657243 5934865.39815112 553186.043 5934865.076 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_ABE5ACD8-E8D2-4746-8BC9-C7887E1A78E1" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553186.043 5934865.076 553188.507 5934865.671 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_E8F0EF0F-D611-4F84-B2F3-B72FDFB87886" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553188.507 5934865.671 553190.526427202 5934865.6428734 553192.235 5934864.566 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_972EE74C-B8CC-4C4B-A829-1F7F75E9A181" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.235 5934864.566 553193.227 5934865.341 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_BBADD0D5-AAC9-49D6-925F-B1840ACF76EC">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553180.8217 5934878.2269</gml:lowerCorner>
          <gml:upperCorner>553180.8217 5934878.2269</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4b97d1c5-89ef-4fe9-a1f8-94a5b06f3144" />
      <xplan:position>
        <gml:Point gml:id="Gml_A30AB62C-A989-4B5D-8833-822CE23C0F39" srsName="EPSG:25832">
          <gml:pos>553180.8217 5934878.2269</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_CA84EBEA-932F-401F-8790-FAF79D0AEAD5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553182.7677 5934875.8294</gml:lowerCorner>
          <gml:upperCorner>553182.7677 5934875.8294</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GRZ</xplan:art>      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4b97d1c5-89ef-4fe9-a1f8-94a5b06f3144" />
      <xplan:position>
        <gml:Point gml:id="Gml_12CBB985-985D-433B-8568-DEC4845596F0" srsName="EPSG:25832">
          <gml:pos>553182.7677 5934875.8294</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_4A626CE9-D662-4B6C-8CB6-D0DB8859D836">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553183.998 5934873.091</gml:lowerCorner>
          <gml:upperCorner>553183.998 5934873.091</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>     
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4b97d1c5-89ef-4fe9-a1f8-94a5b06f3144" />
      <xplan:position>
        <gml:Point gml:id="Gml_DFF3B9C7-61FB-4CF1-B0A3-E31C23FEAA92" srsName="EPSG:25832">
          <gml:pos>553183.998 5934873.091</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_1E99076D-213D-448D-8E8D-98F213F47A3D">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553187.4508 5934867.8125</gml:lowerCorner>
          <gml:upperCorner>553187.4508 5934867.8125</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4b97d1c5-89ef-4fe9-a1f8-94a5b06f3144" />
      <xplan:position>
        <gml:Point gml:id="Gml_DB86E89A-0F9B-4D5C-A4D9-5AF40FA525BF" srsName="EPSG:25832">
          <gml:pos>553187.4508 5934867.8125</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_f2191523-0f54-4964-a6b2-b722842fa561">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553081.048 5934963.792</gml:lowerCorner>
          <gml:upperCorner>553142.02 5935025.162</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_48aeee0c-3768-4859-ad0d-a005916f217f" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_6DF1AA82-043A-4E77-9B5C-8DD328D59A16" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_AA5620E3-4AB3-46B0-99A3-009D6EE86FB5" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_A727FB59-39E0-4B42-9FD4-D8FD6EF7B517" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553137.363 5934972.324 553137.559 5934972.772 553138.333 5934974.536 
553139.802 5934977.884 553142.02 5934983.676 553139.784 5934987.18 
553135.473 5934992.573 553129.061 5934996.69 553117.725 5935002.984 
553115.575 5935004.177 553108.126 5935010.891 553107.768 5935011.213 
553102.798 5935017.826 553101.782 5935019.351 553101.477 5935025.162 
553094.506 5935016.995 553084.786 5935005.613 553081.048 5935001.594 
553091.729 5934992.677 553091.292 5934982.29 553096.432 5934980.782 
553109.069 5934977.075 553107.236 5934968.106 553133.621 5934963.792 
553137.363 5934972.324 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_48aeee0c-3768-4859-ad0d-a005916f217f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553096.0085 5935010.969</gml:lowerCorner>
          <gml:upperCorner>553096.0085 5935010.969</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_f2191523-0f54-4964-a6b2-b722842fa561" />
      <xplan:position>
        <gml:Point gml:id="Gml_A6DB8EBD-1F68-4D46-AFA1-6F939DCFAB44" srsName="EPSG:25832">
          <gml:pos>553096.0085 5935010.969</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_6DF1AA82-043A-4E77-9B5C-8DD328D59A16">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553123.7619 5934977.6747</gml:lowerCorner>
          <gml:upperCorner>553123.7619 5934977.6747</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_f2191523-0f54-4964-a6b2-b722842fa561" />
      <xplan:position>
        <gml:Point gml:id="Gml_64786AF8-C62D-462B-AB5E-8B5CAD552B5F" srsName="EPSG:25832">
          <gml:pos>553123.7619 5934977.6747</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_AA5620E3-4AB3-46B0-99A3-009D6EE86FB5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553124.8202 5934970.9278</gml:lowerCorner>
          <gml:upperCorner>553124.8202 5934970.9278</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_f2191523-0f54-4964-a6b2-b722842fa561" />
      <xplan:position>
        <gml:Point gml:id="Gml_F1D109D0-37FD-446A-B0F6-287837993628" srsName="EPSG:25832">
          <gml:pos>553124.8202 5934970.9278</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_59353d66-9104-454a-b113-314565fd9267">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553033.842 5934922.389</gml:lowerCorner>
          <gml:upperCorner>553048.021 5934935.327</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_0C36B42B-77FD-48BA-80D9-AB87BBE4ACB3" srsName="EPSG:25832">
          <gml:posList>553048.021 5934929.604 553042.431 5934935.327 553033.842 5934926.908 
553035.892 5934924.867 553038.09 5934922.588 553038.279 5934922.389 
553041.845 5934925.872 553043.005 5934924.688 553048.021 5934929.604 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_3d0ff57e-a9b4-4a3c-bdfb-0de53a973b43">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553067.516 5934853.676</gml:lowerCorner>
          <gml:upperCorner>553149.943 5934907.291</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_bb972aa4-f953-4c06-93d7-f01ea9280de1" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_7fe47f0f-4340-4aee-9ea5-7c1950a268d3" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_D6467550-4FAD-4242-AB6B-8FD767A24AA2" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_7907FB1E-D873-484D-931D-AA84DACDD428" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_E0D5BDEE-FF53-40DF-9FA6-31A594284139" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553091.801 5934907.291 553067.516 5934881.336 553081.41 5934873.453 
553093.69 5934863.988 553095.905 5934861.528 553102.972 5934853.676 
553109.541 5934857.883 553115.221 5934861.52 553126.516 5934868.755 
553128.511 5934865.976 553135.335 5934856.473 553149.943 5934865.779 
553141.873 5934878.592 553140.787 5934879.847 553132.193 5934889.781 
553126.851 5934897.155 553122.427 5934903.263 553108.47 5934893.119 
553104.56 5934890.277 553091.801 5934907.291 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_bb972aa4-f953-4c06-93d7-f01ea9280de1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553108.4307 5934885.6522</gml:lowerCorner>
          <gml:upperCorner>553108.4307 5934885.6522</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3d0ff57e-a9b4-4a3c-bdfb-0de53a973b43" />
      <xplan:position>
        <gml:Point gml:id="Gml_52ED67C8-6FD7-4D96-BBC3-799705234951" srsName="EPSG:25832">
          <gml:pos>553108.4307 5934885.6522</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_7fe47f0f-4340-4aee-9ea5-7c1950a268d3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553112.6916 5934875.6101</gml:lowerCorner>
          <gml:upperCorner>553112.6916 5934875.6101</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3d0ff57e-a9b4-4a3c-bdfb-0de53a973b43" />
      <xplan:position>
        <gml:Point gml:id="Gml_4A8B5644-B82C-4BF2-A44F-466433C50247" srsName="EPSG:25832">
          <gml:pos>553112.6916 5934875.6101</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_D6467550-4FAD-4242-AB6B-8FD767A24AA2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553110.6328 5934880.6356</gml:lowerCorner>
          <gml:upperCorner>553110.6328 5934880.6356</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3d0ff57e-a9b4-4a3c-bdfb-0de53a973b43" />
      <xplan:position>
        <gml:Point gml:id="Gml_3013EBFD-2E89-490D-9A7D-FFF836C062DE" srsName="EPSG:25832">
          <gml:pos>553110.6328 5934880.6356</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_7907FB1E-D873-484D-931D-AA84DACDD428">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553116.3478 5934868.9674</gml:lowerCorner>
          <gml:upperCorner>553116.3478 5934868.9674</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3d0ff57e-a9b4-4a3c-bdfb-0de53a973b43" />
      <xplan:position>
        <gml:Point gml:id="Gml_62399913-1FAB-43E7-BC45-2070B4D86D28" srsName="EPSG:25832">
          <gml:pos>553116.3478 5934868.9674</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_504d163f-8c5f-454f-a223-314236cf807d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553140.101 5934852.063</gml:lowerCorner>
          <gml:upperCorner>553150.328 5934861.841</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_18d9cfab-f977-4a41-9f0f-53d0c44f4aeb" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_22FB3C94-7EC1-4084-907E-91867E972E45" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553150.328 5934856.438 553146.799 5934861.841 553140.101 5934857.467 
553143.63 5934852.063 553150.328 5934856.438 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_18d9cfab-f977-4a41-9f0f-53d0c44f4aeb">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553145.215 5934856.952</gml:lowerCorner>
          <gml:upperCorner>553145.215 5934856.952</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_504d163f-8c5f-454f-a223-314236cf807d" />
      <xplan:position>
        <gml:Point gml:id="Gml_67D4C0CB-5FE4-4EB5-B6D0-E23C73912E4B" srsName="EPSG:25832">
          <gml:pos>553145.215 5934856.952</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_3a693dcd-9e1b-40a1-88c5-b1c92858b2ee">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553166.521 5934939.116</gml:lowerCorner>
          <gml:upperCorner>553194.696 5934961.594</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_83142A8D-47A9-436E-8D37-7E18C2D547CC" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_424654DD-B467-4E99-BBD1-12534871136D" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_4D732989-CC70-4DDB-9C94-510696F06FF8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553177.07 5934942.663 553177.7 5934943.066 553184.403 5934947.356 
553185.432 5934948.014 553187.507 5934949.495 553189.882 5934951.189 
553194.696 5934954.625 553189.433 5934961.594 553182.819 5934957.126 
553181.199 5934956.039 553179.39 5934955.248 553177.876 5934954.81 
553171.904 5934950.732 553166.521 5934947.057 553171.528 5934939.116 
553177.07 5934942.663 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>3</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_83142A8D-47A9-436E-8D37-7E18C2D547CC">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553171.4596 5934945.089</gml:lowerCorner>
          <gml:upperCorner>553171.4596 5934945.089</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3a693dcd-9e1b-40a1-88c5-b1c92858b2ee" />
      <xplan:position>
        <gml:Point gml:id="Gml_5F4272AF-1867-4A10-8FF4-7BABE71FEF2C" srsName="EPSG:25832">
          <gml:pos>553171.4596 5934945.089</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_424654DD-B467-4E99-BBD1-12534871136D">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553188.049 5934955.8046</gml:lowerCorner>
          <gml:upperCorner>553188.049 5934955.8046</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3a693dcd-9e1b-40a1-88c5-b1c92858b2ee" />
      <xplan:position>
        <gml:Point gml:id="Gml_E3187DFD-C823-4DE3-84B4-2ACD585CCC99" srsName="EPSG:25832">
          <gml:pos>553188.049 5934955.8046</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_4aee750d-ade4-4c62-bf12-0d2df01f4cb3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.286 5934964.625</gml:lowerCorner>
          <gml:upperCorner>553166.48 5934972.966</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_df0c88a7-87d3-44c4-bfa9-a8697e35f89b" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_55CDC65C-55AB-4FD7-87E2-0B1EA2326F4B" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553166.48 5934972.331 553160.011 5934972.966 553159.286 5934965.585 
553165.723 5934964.625 553166.48 5934972.331 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_df0c88a7-87d3-44c4-bfa9-a8697e35f89b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553162.844 5934969.16</gml:lowerCorner>
          <gml:upperCorner>553162.844 5934969.16</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4aee750d-ade4-4c62-bf12-0d2df01f4cb3" />
      <xplan:position>
        <gml:Point gml:id="Gml_6FD0DC56-E5B9-4E62-8D91-1DBBDA1F9EC3" srsName="EPSG:25832">
          <gml:pos>553162.844 5934969.16</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_020de115-3e22-4d75-beff-6a946e803528">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553225.471 5934924.006</gml:lowerCorner>
          <gml:upperCorner>553244.104 5934939.315</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_523038EF-7DE4-49DD-868A-E92FFA98FD57" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553231.278 5934937.864 553231.158 5934939.315 553228.861 5934939.166 
553228.976 5934937.498 553225.471 5934932.064 553230.15 5934929.046 
553230.882 5934930.181 553233.379 5934928.578 553232.643 5934927.437 
553236.491 5934924.955 553237.23 5934926.106 553240.502 5934924.006 
553244.104 5934929.589 553238.558 5934933.168 553239.342 5934934.383 
553235.209 5934936.971 553234.461 5934935.811 553231.278 5934937.864 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_a7a1fbfb-7f16-46cb-b777-b601262f9d70">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553103.725 5935053.885</gml:lowerCorner>
          <gml:upperCorner>553115.304 5935065.97</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_C6D28C83-73E1-4A09-A8F9-EF1034DE42D0" srsName="EPSG:25832">
          <gml:posList>553103.725 5935065.166 553104.452 5935053.885 553115.304 5935054.657 
553114.446 5935065.97 553103.725 5935065.166 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_4554843f-6bde-4807-835d-c3449e36e56d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.894 5934896.056</gml:lowerCorner>
          <gml:upperCorner>553192.546 5934934.153</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_fd5ed2d4-4a70-4f02-9cb2-485e4fa6dd79" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ab75fa47-0243-4d15-9ec4-f095991c575b" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_db92ae6b-8c00-4f15-b897-bfae3abd7bca" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_82322A9E-FEB6-4260-AF86-E86AFE13C2CD" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553192.546 5934908.48 553181.662 5934919.772 553180.645 5934920.789 
553176.762 5934924.672 553173.742 5934927.691 553172.659 5934928.499 
553167.982 5934931.983 553164.759 5934933.554 553162.208 5934934.153 
553160.51 5934932.7 553159.894 5934928.518 553160.454 5934921.468 
553160.908 5934920.802 553164.182 5934917.711 553167.986 5934914.118 
553171.133 5934911.148 553171.7 5934910.53 553179.316 5934902.227 
553183.41 5934896.056 553192.546 5934908.48 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:Z>1</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_fd5ed2d4-4a70-4f02-9cb2-485e4fa6dd79">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553175.276 5934912.729</gml:lowerCorner>
          <gml:upperCorner>553175.276 5934912.729</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4554843f-6bde-4807-835d-c3449e36e56d" />
      <xplan:position>
        <gml:Point gml:id="Gml_18B60C8F-EF11-474F-B8A0-DAE26689B116" srsName="EPSG:25832">
          <gml:pos>553175.276 5934912.729</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ab75fa47-0243-4d15-9ec4-f095991c575b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553178.655 5934918.15</gml:lowerCorner>
          <gml:upperCorner>553178.655 5934918.15</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4554843f-6bde-4807-835d-c3449e36e56d" />
      <xplan:position>
        <gml:Point gml:id="Gml_E3247A2E-FE94-4F62-B913-EE690364C366" srsName="EPSG:25832">
          <gml:pos>553178.655 5934918.15</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_db92ae6b-8c00-4f15-b897-bfae3abd7bca">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553172.619 5934918.234</gml:lowerCorner>
          <gml:upperCorner>553172.619 5934918.234</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4554843f-6bde-4807-835d-c3449e36e56d" />
      <xplan:position>
        <gml:Point gml:id="Gml_542D6735-AC4F-4137-A2B4-9CAB11438335" srsName="EPSG:25832">
          <gml:pos>553172.619 5934918.234</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_362ffa88-bca9-452f-9975-dbd438799fc5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.047 5934942.598</gml:lowerCorner>
          <gml:upperCorner>553153.755 5934955.485</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_CDE5C5D5-F87C-4160-82B6-EA51690D5A36" srsName="EPSG:25832">
          <gml:posList>553143.047 5934945.008 553150.237 5934942.598 553153.755 5934953.07 
553146.562 5934955.485 553143.047 5934945.008 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_33ea7edf-8b8a-4f4c-a1d6-f166e89e4837">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552949.495 5934955.361</gml:lowerCorner>
          <gml:upperCorner>552974.219 5934968.897</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_9B7D7B59-E1BA-4053-B170-42D78D416062" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552972.354 5934955.689 552974.219 5934960.018 552952.039 5934968.897 
552949.495 5934965.341 552955.243 5934962.569 552972.213 5934955.361 
552972.354 5934955.689 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_4268ceb8-821c-4cab-9aa2-393596c52357">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553053.654 5934881.336</gml:lowerCorner>
          <gml:upperCorner>553100.553 5934923.789</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_061681e8-debb-42d8-a05e-52ae3a583be5" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_80f04a76-1034-4dd2-9278-2f524cef9995" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_08935D60-83A6-430F-BD1C-A7BCB47180AD" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_AB302B2A-C1A9-48CD-B489-E91AF3E1F641" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553086.698 5934922.176 553086.689 5934923.789 553068.183 5934923.751 
553053.761 5934908.007 553053.654 5934907.891 553060.597 5934905.059 
553072.83 5934900.369 553078.21 5934898.615 553063.87 5934883.291 
553067.516 5934881.336 553091.801 5934907.291 553100.553 5934916.647 
553095.717 5934916.619 553086.73 5934916.566 553086.698 5934922.176 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>6</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_061681e8-debb-42d8-a05e-52ae3a583be5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553074.869 5934904.405</gml:lowerCorner>
          <gml:upperCorner>553074.869 5934904.405</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4268ceb8-821c-4cab-9aa2-393596c52357" />
      <xplan:position>
        <gml:Point gml:id="Gml_7FC362A0-75B8-428F-9045-92EFA3F35163" srsName="EPSG:25832">
          <gml:pos>553074.869 5934904.405</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_80f04a76-1034-4dd2-9278-2f524cef9995">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553072.159 5934920.142</gml:lowerCorner>
          <gml:upperCorner>553072.159 5934920.142</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4268ceb8-821c-4cab-9aa2-393596c52357" />
      <xplan:position>
        <gml:Point gml:id="Gml_700D58CB-0409-45BC-9681-8E55D6162897" srsName="EPSG:25832">
          <gml:pos>553072.159 5934920.142</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_08935D60-83A6-430F-BD1C-A7BCB47180AD">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553072.939 5934912.1089</gml:lowerCorner>
          <gml:upperCorner>553072.939 5934912.1089</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4268ceb8-821c-4cab-9aa2-393596c52357" />
      <xplan:position>
        <gml:Point gml:id="Gml_AFBEDD3B-EDE2-48D1-B7FB-382A5C26392E" srsName="EPSG:25832">
          <gml:pos>553072.939 5934912.1089</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_e34643be-758c-466c-8e04-dbe2fc820932">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553032.689 5934896.603</gml:lowerCorner>
          <gml:upperCorner>553045.153 5934909.058</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_3D5D2DBD-D710-4D3C-B11D-BB590B571A65" srsName="EPSG:25832">
          <gml:posList>553045.153 5934903.009 553038.637 5934909.058 553032.689 5934902.653 
553039.204 5934896.603 553045.153 5934903.009 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_0e61f84f-4a6f-44f0-9b4b-3b0ba33c82b2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553068.183 5934916.566</gml:lowerCorner>
          <gml:upperCorner>553105.81 5934938.869</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_124975c1-347c-4cdd-a946-7091b5cf6b34" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_15c980ea-21cb-4765-85f3-30e46f0db717" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_88B5A80D-9099-4B77-AC0B-9CCC29E6ED11" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_365E9D99-236E-4C6F-90EF-93C937BF7865" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553103.961 5934923.937 553104.209 5934924.78 553105.81 5934933.542 
553103.012 5934935.292 553100.164 5934937.073 553097.292 5934938.869 
553080.833 5934938.839 553075.161 5934931.367 553068.183 5934923.751 
553086.689 5934923.789 553086.698 5934922.176 553086.73 5934916.566 
553095.717 5934916.619 553100.553 5934916.647 553101.537 5934917.698 
553102.403 5934918.623 553103.961 5934923.937 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>6</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_124975c1-347c-4cdd-a946-7091b5cf6b34">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553093.4975 5934933.4034</gml:lowerCorner>
          <gml:upperCorner>553093.4975 5934933.4034</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0e61f84f-4a6f-44f0-9b4b-3b0ba33c82b2" />
      <xplan:position>
        <gml:Point gml:id="Gml_BD50F301-AB73-49D1-8988-C2A56C50EB30" srsName="EPSG:25832">
          <gml:pos>553093.4975 5934933.4034</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_15c980ea-21cb-4765-85f3-30e46f0db717">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553081.2631 5934933.6383</gml:lowerCorner>
          <gml:upperCorner>553081.2631 5934933.6383</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0e61f84f-4a6f-44f0-9b4b-3b0ba33c82b2" />
      <xplan:position>
        <gml:Point gml:id="Gml_6DFD5EE8-B162-4FA4-8EFD-F2B271B5B0E6" srsName="EPSG:25832">
          <gml:pos>553081.2631 5934933.6383</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_88B5A80D-9099-4B77-AC0B-9CCC29E6ED11">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553079.6594 5934928.2882</gml:lowerCorner>
          <gml:upperCorner>553079.6594 5934928.2882</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0e61f84f-4a6f-44f0-9b4b-3b0ba33c82b2" />
      <xplan:position>
        <gml:Point gml:id="Gml_D114977D-BB1B-4A32-8FA3-41194C86DC36" srsName="EPSG:25832">
          <gml:pos>553079.6594 5934928.2882</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_919adfd5-d0e0-467b-9f1e-2d1406abcb9d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553148.487 5934902.753</gml:lowerCorner>
          <gml:upperCorner>553164.317 5934916.05</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_FE1D3A7B-EC41-4F40-9716-DE9403429A52" srsName="EPSG:25832">
          <gml:posList>553148.487 5934910.024 553153.216 5934902.753 553164.317 5934909.971 
553161.137 5934916.05 553156.598 5934915.274 553148.487 5934910.024 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_62ac6793-53d6-494d-a202-c30d30dbd8a7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553012.997 5934901.179</gml:lowerCorner>
          <gml:upperCorner>553029.294 5934915.092</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_EA270060-1078-4CFB-96A5-96995C27F206" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553026.911 5934911.448 553023.296 5934915.092 553019.399 5934911.225 
553017.496 5934913.142 553012.997 5934908.676 553018.247 5934903.39 
553019.138 5934904.274 553022.207 5934901.179 553029.294 5934908.199 
553026.488 5934911.029 553026.911 5934911.448 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552966.031 5934927.822</gml:lowerCorner>
          <gml:upperCorner>552995.787 5934961.949</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ff4c6ea4-f7cd-4dcd-a244-6524759e83e1" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0fa5f074-3ac5-4f0f-b68c-7cd7076ca862" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_5a4bed7f-7926-4a75-a550-6f35f8b83f1d" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_D72AECB0-ECE7-4612-984C-06B8EABD3252" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_89D5C38F-0342-4603-B77F-AAE4434597E8" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_531174E8-AAC9-49D2-8C1A-A51D987805BB" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552992.007 5934940.16 552995.787 5934958.72 552991.593 5934959.206 
552990.765 5934959.302 552975.051 5934961.949 552974.704 5934961.145 
552974.219 5934960.018 552972.354 5934955.689 552972.213 5934955.361 
552966.031 5934941.015 552968.262 5934939.851 552967.852 5934938.957 
552989.494 5934927.822 552992.007 5934940.16 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ff4c6ea4-f7cd-4dcd-a244-6524759e83e1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552977.847 5934942.708</gml:lowerCorner>
          <gml:upperCorner>552977.847 5934942.708</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5" />
      <xplan:position>
        <gml:Point gml:id="Gml_91EE3DA0-2E0A-46FD-A323-86D9C9D8FC0C" srsName="EPSG:25832">
          <gml:pos>552977.847 5934942.708</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0fa5f074-3ac5-4f0f-b68c-7cd7076ca862">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552978.933 5934955.069</gml:lowerCorner>
          <gml:upperCorner>552978.933 5934955.069</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5" />
      <xplan:position>
        <gml:Point gml:id="Gml_4B3BCB38-AD04-4FE3-B6A6-99B75C95144A" srsName="EPSG:25832">
          <gml:pos>552978.933 5934955.069</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_5a4bed7f-7926-4a75-a550-6f35f8b83f1d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552985.334 5934955.088</gml:lowerCorner>
          <gml:upperCorner>552985.334 5934955.088</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5" />
      <xplan:position>
        <gml:Point gml:id="Gml_517587B2-8287-4332-9F8F-3D2300CCCC67" srsName="EPSG:25832">
          <gml:pos>552985.334 5934955.088</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_D72AECB0-ECE7-4612-984C-06B8EABD3252">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552978.9244 5934948.7644</gml:lowerCorner>
          <gml:upperCorner>552978.9244 5934948.7644</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5" />
      <xplan:position>
        <gml:Point gml:id="Gml_4D9F9908-6F73-44EE-BED5-23906BAC1587" srsName="EPSG:25832">
          <gml:pos>552978.9244 5934948.7644</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_89D5C38F-0342-4603-B77F-AAE4434597E8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552983.0519 5934935.7998</gml:lowerCorner>
          <gml:upperCorner>552983.0519 5934935.7998</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_530f19fc-ae0a-4328-b2c8-ac4cce28a3c5" />
      <xplan:position>
        <gml:Point gml:id="Gml_6B52B700-80C4-400E-AC81-503E015E3CA8" srsName="EPSG:25832">
          <gml:pos>552983.0519 5934935.7998</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:SO_Denkmalschutzrecht gml:id="GML_5a5f4648-b0b7-4de0-be18-5bfd2170bbcd">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553165.374 5934933.212</gml:lowerCorner>
          <gml:upperCorner>553299.972 5935070.404</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_7B4B56B4-86A5-4C83-9136-D2C78D950539" />
      <xplan:rechtscharakter>5000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0510E064-B9CC-4759-8163-A0E043BE66EB" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553253.356 5934985.837 553261.183 5934997.286 553264.006 5935001.415 
553274.278 5935016.443 553299.972 5935051.125 553294.9752 5935054.3542 
553258.2483 5935066.594 553242.1183 5935067.0238 553230.74 5935070.32 
553225.953 5935070.404 553209.094 5935069.728 553190.206 5935069.693 
553184.408 5935058.141 553176.261 5935047.118 553168.862 5935026.498 
553165.374 5935017.085 553173.912 5935015.05 553169.567 5934985.588 
553169.074 5934978.061 553168.921 5934975.398 553180.889 5934977.757 
553184.472 5934969.866 553189.433 5934961.594 553194.696 5934954.625 
553198.992 5934947.919 553210.329 5934936.179 553215.029 5934933.212 
553253.356 5934985.837 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:artDerFestlegung>1000</xplan:artDerFestlegung>
    </xplan:SO_Denkmalschutzrecht>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_7B4B56B4-86A5-4C83-9136-D2C78D950539">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553249.5849 5935034.4885</gml:lowerCorner>
          <gml:upperCorner>553249.5849 5935034.4885</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>artDerFestlegung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5a5f4648-b0b7-4de0-be18-5bfd2170bbcd" />
      <xplan:position>
        <gml:Point gml:id="Gml_C5989D42-A033-4613-9A77-63FC402BC1FA" srsName="EPSG:25832">
          <gml:pos>553249.5849 5935034.4885</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_b7d34767-ee95-4d67-9410-0d02e9ca8daa">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553094.811 5934986.48</gml:lowerCorner>
          <gml:upperCorner>553109.578 5935002.028</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_80935cd5-1b2c-48a4-9240-8d56a4603d07" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_5AEA4E4A-523A-47D9-9037-E533844D5F9F" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553109.578 5934992.563 553103.651 5935002.028 553095.979 5934997.224 
553096.848 5934995.833 553094.811 5934994.56 553099.86 5934986.48 
553109.578 5934992.563 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_80935cd5-1b2c-48a4-9240-8d56a4603d07">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.194 5934993.558</gml:lowerCorner>
          <gml:upperCorner>553102.194 5934993.558</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_b7d34767-ee95-4d67-9410-0d02e9ca8daa" />
      <xplan:position>
        <gml:Point gml:id="Gml_C80A99C7-4908-430B-A69C-6F5854FBCF7D" srsName="EPSG:25832">
          <gml:pos>553102.194 5934993.558</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_eecb61a3-904e-49af-b5c6-f970bbc9b575">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552989.494 5934927.822</gml:lowerCorner>
          <gml:upperCorner>552995.787 5934958.72</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_2FE953C6-7988-4A6E-8FE5-E5919E6D5EEE" srsName="EPSG:25832">
          <gml:posList>552989.494 5934927.822 552992.007 5934940.16 552995.787 5934958.72 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_38ff6c4b-667c-4186-93ac-2e654bfd62e7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553071.815 5935035.599</gml:lowerCorner>
          <gml:upperCorner>553088.578 5935053.445</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_79DF0DF8-5453-4FC4-A573-CEBBDE4922CE" srsName="EPSG:25832">
          <gml:posList>553085.173 5935045.849 553086.338 5935046.836 553080.725 5935053.445 
553071.815 5935045.881 553080.537 5935035.599 553088.578 5935041.839 
553085.173 5935045.849 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_dc87f173-784c-4b93-876a-ff853cd2d759">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553025.713 5934982.262</gml:lowerCorner>
          <gml:upperCorner>553047.71 5935006.263</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_45f5f309-20a2-40e1-924e-24c281c9c493" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_A5750FA7-FC11-4253-9BF7-285906AAB9C7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553041.209 5934987.02 553039.781 5934988.191 553047.71 5934997.886 
553037.468 5935006.263 553025.713 5934991.709 553037.526 5934982.262 
553041.209 5934987.02 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_45f5f309-20a2-40e1-924e-24c281c9c493">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553036.761 5934994.363</gml:lowerCorner>
          <gml:upperCorner>553036.761 5934994.363</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_dc87f173-784c-4b93-876a-ff853cd2d759" />
      <xplan:position>
        <gml:Point gml:id="Gml_3A9CE74A-DC6C-4C80-A67D-8576ED5D834B" srsName="EPSG:25832">
          <gml:pos>553036.761 5934994.363</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_c1fc4d1a-7ac6-41c9-8b77-c82b074101c9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553025.567 5934935.381</gml:lowerCorner>
          <gml:upperCorner>553038.441 5934946.009</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_37AD5806-5127-494A-9DDD-510A28DE30E1" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553038.441 5934940.361 553036.422 5934941.056 553037.072 5934942.948 
553028.169 5934946.009 553025.567 5934938.444 553034.473 5934935.381 
553035.377 5934938.013 553037.394 5934937.319 553038.441 5934940.361 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_c8ee2d13-db90-4144-81bc-0c156911b4f5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553090.67 5934966.16</gml:lowerCorner>
          <gml:upperCorner>553109.069 5934982.29</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_c7a392b3-e5bc-42d3-bd3f-baa010592ffc" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_9a6be2c2-4816-4489-8793-a9c427660f5c" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_8be3b99b-c2d9-4a58-877e-b71d4db77638" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_1C715386-85EF-44E3-99F7-0665B749B6C5" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_13913ABC-2AB3-40F9-8A67-6F6C905BE962" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553107.236 5934968.106 553109.069 5934977.075 553096.432 5934980.782 
553091.292 5934982.29 553090.672 5934967.572 553090.67 5934967.534 
553106.838 5934966.16 553107.236 5934968.106 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.5</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_c7a392b3-e5bc-42d3-bd3f-baa010592ffc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553103.393 5934977.746</gml:lowerCorner>
          <gml:upperCorner>553103.393 5934977.746</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c8ee2d13-db90-4144-81bc-0c156911b4f5" />
      <xplan:position>
        <gml:Point gml:id="Gml_B4C71A18-C8EA-4FCC-8EB0-D34167BCCE17" srsName="EPSG:25832">
          <gml:pos>553103.393 5934977.746</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_9a6be2c2-4816-4489-8793-a9c427660f5c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553096.934 5934977.816</gml:lowerCorner>
          <gml:upperCorner>553096.934 5934977.816</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c8ee2d13-db90-4144-81bc-0c156911b4f5" />
      <xplan:position>
        <gml:Point gml:id="Gml_F68FEDEB-184F-4E23-86D9-9FE27A0310CB" srsName="EPSG:25832">
          <gml:pos>553096.934 5934977.816</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_8be3b99b-c2d9-4a58-877e-b71d4db77638">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553098.1129 5934969.328</gml:lowerCorner>
          <gml:upperCorner>553098.1129 5934969.328</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c8ee2d13-db90-4144-81bc-0c156911b4f5" />
      <xplan:position>
        <gml:Point gml:id="Gml_3BEBAEC0-F938-45D9-914E-0B7D5A158911" srsName="EPSG:25832">
          <gml:pos>553098.1129 5934969.328</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_1C715386-85EF-44E3-99F7-0665B749B6C5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553098.936 5934973.6663</gml:lowerCorner>
          <gml:upperCorner>553098.936 5934973.6663</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c8ee2d13-db90-4144-81bc-0c156911b4f5" />
      <xplan:position>
        <gml:Point gml:id="Gml_05BAE387-E46B-43E2-B26A-AFE99CA5089A" srsName="EPSG:25832">
          <gml:pos>553098.936 5934973.6663</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_c43ec757-6459-4d49-a9e8-23cb8242782f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553124.822 5934874.942</gml:lowerCorner>
          <gml:upperCorner>553135.487 5934885.006</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_07CFE8BE-F7A8-4D60-AD8F-AD3A7B7C1695" srsName="EPSG:25832">
          <gml:posList>553135.487 5934879.538 553131.972 5934885.006 553124.822 5934880.409 
553128.337 5934874.942 553135.487 5934879.538 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_89bf84bc-3686-467d-b144-895a1e432dce">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553177.7 5934937.183</gml:lowerCorner>
          <gml:upperCorner>553189.316 5934948.014</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_4ad10e2c-083e-440f-84a3-9ddc5c488a2e" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_DADD8470-BD2D-470B-AD90-193A8302FA36" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553189.316 5934942.096 553186.952 5934945.761 553185.432 5934948.014 
553184.403 5934947.356 553177.7 5934943.066 553181.468 5934937.183 
553186.681 5934940.545 553186.748 5934940.439 553189.316 5934942.096 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_4ad10e2c-083e-440f-84a3-9ddc5c488a2e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553182.279 5934939.079</gml:lowerCorner>
          <gml:upperCorner>553182.279 5934939.079</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_89bf84bc-3686-467d-b144-895a1e432dce" />
      <xplan:position>
        <gml:Point gml:id="Gml_DEE2FC24-54D2-45CB-B1B6-375CF27237F4" srsName="EPSG:25832">
          <gml:pos>553182.279 5934939.079</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_473aaa80-8378-46f7-97fa-75d210f8d2ca">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552995.018 5934910.539</gml:lowerCorner>
          <gml:upperCorner>553018.757 5934933.426</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D12D702A-A33B-4A14-A29A-215D9A4B34BB" srsName="EPSG:25832">
          <gml:posList>553003.501 5934933.426 552995.018 5934924.939 553009.487 5934910.539 
553018.757 5934919.832 553011.524 5934927.044 553010.722 5934926.24 
553003.501 5934933.426 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_84db8d8f-fa25-4cb4-ad12-9b3ec8bc62f8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553117.951 5934927.222</gml:lowerCorner>
          <gml:upperCorner>553127.533 5934934.627</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ad508406-e9b2-4889-9199-0673975bd12a" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_FB7C8383-D923-4328-A6CF-03DDBFC27C0B" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553127.533 5934933.759 553118.585 5934934.627 553117.951 5934928.092 
553126.898 5934927.222 553127.533 5934933.759 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ad508406-e9b2-4889-9199-0673975bd12a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553122.831 5934932.586</gml:lowerCorner>
          <gml:upperCorner>553122.831 5934932.586</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_84db8d8f-fa25-4cb4-ad12-9b3ec8bc62f8" />
      <xplan:position>
        <gml:Point gml:id="Gml_19AB31B6-7B12-4BC7-A13C-2E2E110DF93A" srsName="EPSG:25832">
          <gml:pos>553122.831 5934932.586</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_5d1a4b8c-78a9-4a48-a1dd-d98e08bc6e96">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553085.265 5935046.053</gml:lowerCorner>
          <gml:upperCorner>553101.664 5935062.584</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_70E88415-D864-41DC-AFA3-153D64FF4025" srsName="EPSG:25832">
          <gml:posList>553095.738 5935062.584 553085.265 5935057.436 553087.436 5935053.023 
553088.732 5935053.66 553092.475 5935046.053 553101.664 5935050.545 
553095.738 5935062.584 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_623528b6-4a6b-4e8b-a719-dab1ea62d223">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553095.689 5935038.171</gml:lowerCorner>
          <gml:upperCorner>553117.936 5935071.436</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_39c00556-6f68-4373-a1fa-4afca30e8044" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_b0d4b2e3-f164-47f0-b8f8-0bdc6476515f" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_479FEDB1-78EA-4831-9DE7-7EB25C1A1246" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_098DA8FD-00FF-40E9-B91E-3C85E9363BEF" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_3737F13F-084F-4063-83F1-21860178AC2A" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_4CBAF163-0923-430F-A7BD-C426A44A1743" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553115.737 5935069.696 553110.286565974 5935068.68207358 553105.475 5935071.436 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7CB5E662-4E73-4134-B4AA-D98CD4845757" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553105.475 5935071.436 553103.762 5935071.226 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E1FAAB16-397F-40D2-8A3A-50A1BE96B664" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553103.762 5935071.226 553097.811 5935070.423 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_03A13C51-0B96-42DC-93AD-0F35B8BC9736" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553097.811 5935070.423 553095.689 5935069.942 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B30180CE-AAA7-4C56-802C-8B8F4AFFF9BF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553095.689 5935069.942 553110.462 5935038.171 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F4D3E044-E956-4944-9BB3-3E68F39290E0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553110.462 5935038.171 553113.536 5935040.973 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_68C06A2C-CEC4-432A-8642-4743A9FAED45" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553113.536 5935040.973 553116.587 5935049.381 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EC142100-75C1-42E2-B280-6D0F19C15D24" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553116.587 5935049.381 553117.936 5935059.163 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_26D1A06E-1883-4D8E-AA40-D7A0DEA22C01" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553117.936 5935059.163 553117.52 5935066.961 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_63364784-0551-402D-B5D2-DB33111FA549" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553117.52 5935066.961 553115.737 5935069.696 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_39c00556-6f68-4373-a1fa-4afca30e8044">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553101.2831 5935066.8563</gml:lowerCorner>
          <gml:upperCorner>553101.2831 5935066.8563</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_623528b6-4a6b-4e8b-a719-dab1ea62d223" />
      <xplan:position>
        <gml:Point gml:id="Gml_9B7FD6DC-F56F-4978-BDC6-21BAEC9F2E73" srsName="EPSG:25832">
          <gml:pos>553101.2831 5935066.8563</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_b0d4b2e3-f164-47f0-b8f8-0bdc6476515f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553109.2452 5935058.1534</gml:lowerCorner>
          <gml:upperCorner>553109.2452 5935058.1534</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_623528b6-4a6b-4e8b-a719-dab1ea62d223" />
      <xplan:position>
        <gml:Point gml:id="Gml_A3103B85-1903-4DE9-882A-70B5B1A58862" srsName="EPSG:25832">
          <gml:pos>553109.2452 5935058.1534</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_479FEDB1-78EA-4831-9DE7-7EB25C1A1246">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553110.2483 5935050.2534</gml:lowerCorner>
          <gml:upperCorner>553110.2483 5935050.2534</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_623528b6-4a6b-4e8b-a719-dab1ea62d223" />
      <xplan:position>
        <gml:Point gml:id="Gml_594DFC08-1156-4883-8415-ECBFEDDDDAEA" srsName="EPSG:25832">
          <gml:pos>553110.2483 5935050.2534</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_098DA8FD-00FF-40E9-B91E-3C85E9363BEF">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553110.8833 5935045.4115</gml:lowerCorner>
          <gml:upperCorner>553110.8833 5935045.4115</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_623528b6-4a6b-4e8b-a719-dab1ea62d223" />
      <xplan:position>
        <gml:Point gml:id="Gml_DF8C90A1-7C4B-4A03-8545-9F38A43597FD" srsName="EPSG:25832">
          <gml:pos>553110.8833 5935045.4115</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_4c803914-471b-4f49-aafe-b02af0d2fad7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553067.516 5934881.336</gml:lowerCorner>
          <gml:upperCorner>553105.81 5934933.542</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_13888933-7262-4BFC-AFCA-130D1215DA36" srsName="EPSG:25832">
          <gml:posList>553067.516 5934881.336 553091.801 5934907.291 553100.553 5934916.647 
553101.537 5934917.698 553102.403 5934918.623 553103.961 5934923.937 
553104.209 5934924.78 553105.81 5934933.542 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_0ef74ac8-a0fa-451b-8cf6-a29ae1bfd0a7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.403 5934913.037</gml:lowerCorner>
          <gml:upperCorner>553116.196 5934923.937</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_FB6C44C3-8E32-4AAD-997E-85C86558549D" srsName="EPSG:25832">
          <gml:posList>553114.831 5934913.037 553114.916 5934913.237 553116.196 5934916.245 
553111.901 5934918.073 553112.85 5934920.301 553103.961 5934923.937 
553102.403 5934918.623 553114.831 5934913.037 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_1e12d354-6480-4756-bf94-1cab0c5cbd0c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553046.32 5934890.284</gml:lowerCorner>
          <gml:upperCorner>553062.824 5934901.884</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_CF63B5D2-99ED-469E-AE61-EF1409F3ADAD" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553061.165 5934899.023 553050.285 5934901.884 553049.491 5934898.868 
553047.193 5934899.473 553046.32 5934896.159 553048.761 5934895.517 
553048.243 5934893.551 553048.131 5934893.128 553058.877 5934890.284 
553059.028 5934890.855 553059.699 5934893.393 553061.953 5934892.799 
553062.824 5934896.098 553060.552 5934896.696 553061.165 5934899.023 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552992.412 5934957.607</gml:lowerCorner>
          <gml:upperCorner>553050.42 5934991.971</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_2ebaff1b-6cdd-4257-9dbe-bae2e6f8a01f" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_71faf3d6-41c7-4bce-98bd-b05b7976ac67" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_524ab7c4-d4e7-43c4-8893-303cffbf51ce" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_F43A2B52-4442-445A-BA56-419C46DABC6E" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_2339E194-0F76-4F7E-AE83-8EACA56D0FD6" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_24A38CDA-1C37-4BCD-858C-2D4A08F29D6A" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_68D4E886-C035-4B8B-90A5-AD7CF6EC18F7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553043.265 5934962.917 553050.42 5934968.139 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_1FE73BB2-874A-4D68-9EC5-66E6C9B83DD2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553050.42 5934968.139 553018.342 5934991.971 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2803801B-DABE-4368-B498-4B25CF45EDEB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553018.342 5934991.971 553017.322 5934990.798 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_B57963B7-0D68-4318-9132-479EBE90D332" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553017.322 5934990.798 553016.486967286 5934989.75996394 553015.535 5934988.828 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_BCF217CC-05B4-4382-9EC0-97B1A7D763AE" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553015.535 5934988.828 553014.366585597 5934987.49655946 553013.109 5934986.249 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_6664024F-F746-49E3-A45E-449CA2094BA4" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553013.109 5934986.249 553012.831989058 5934985.93598812 553012.543 5934985.634 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_16188DFD-42E9-43CB-86E1-35250DAD08DC" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553012.543 5934985.634 553012.095874911 5934985.20788719 553011.626 5934984.807 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_954B2A93-6540-46FD-9F83-30B91C117CDE" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553011.626 5934984.807 553009.803215356 5934983.40416606 553007.983 5934981.998 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_4A1941A2-92AC-49B6-BE4D-F7C2B73C4066" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553007.983 5934981.998 553007.206699469 5934981.38877603 553006.401 5934980.819 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_4EEABF80-9C3E-4939-92EF-53540329ED2D" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553006.401 5934980.819 553005.261069704 5934980.02704046 553004.008 5934979.43 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_6039EF07-369D-4E89-9197-6F2D96A3268D" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553004.008 5934979.43 553002.415580336 5934978.63379626 553000.805 5934977.875 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_B869E665-CE4E-44D4-857A-A22468A6A7AF" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553000.805 5934977.875 552998.753979175 5934977.11199307 552996.655 5934976.493 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_DA2C65DC-918E-40AB-A668-97B1BE95FDDE" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">552996.655 5934976.493 552994.788164679 5934975.98703473 552992.876 5934975.696 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3C9E675C-BB31-49DD-9956-B3FEC7192D08" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552992.876 5934975.696 552992.412 5934969.734 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D23D7789-92B7-4F37-BC58-7D54B777F75E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552992.412 5934969.734 553017.281 5934964.074 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E4D10208-3C42-4A4E-BC69-56ED28B98515" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553017.281 5934964.074 553035.989 5934957.607 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B914CD65-64A2-435D-9B8E-DE2A16ECEB4A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553035.989 5934957.607 553043.265 5934962.917 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_2ebaff1b-6cdd-4257-9dbe-bae2e6f8a01f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553032.2154 5934973.0241</gml:lowerCorner>
          <gml:upperCorner>553032.2154 5934973.0241</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e" />
      <xplan:position>
        <gml:Point gml:id="Gml_BC70E1B6-A29B-49A3-913B-D66064CD469A" srsName="EPSG:25832">
          <gml:pos>553032.2154 5934973.0241</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_71faf3d6-41c7-4bce-98bd-b05b7976ac67">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553019.753 5934982.945</gml:lowerCorner>
          <gml:upperCorner>553019.753 5934982.945</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e" />
      <xplan:position>
        <gml:Point gml:id="Gml_1DAFBBE3-3359-4483-BECE-CF5C985FC531" srsName="EPSG:25832">
          <gml:pos>553019.753 5934982.945</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_524ab7c4-d4e7-43c4-8893-303cffbf51ce">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553026.079 5934982.824</gml:lowerCorner>
          <gml:upperCorner>553026.079 5934982.824</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e" />
      <xplan:position>
        <gml:Point gml:id="Gml_9B05184D-47AA-4BDD-900D-DFD1DCF372B3" srsName="EPSG:25832">
          <gml:pos>553026.079 5934982.824</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_F43A2B52-4442-445A-BA56-419C46DABC6E">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553028.3133 5934977.979</gml:lowerCorner>
          <gml:upperCorner>553028.3133 5934977.979</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e" />
      <xplan:position>
        <gml:Point gml:id="Gml_3DEA5F91-3B2B-47C1-8433-599F14CD5D3E" srsName="EPSG:25832">
          <gml:pos>553028.3133 5934977.979</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_2339E194-0F76-4F7E-AE83-8EACA56D0FD6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553033.3404 5934965.6494</gml:lowerCorner>
          <gml:upperCorner>553033.3404 5934965.6494</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e67f26ba-efc9-44c2-8cb8-c2a31e7dd18e" />
      <xplan:position>
        <gml:Point gml:id="Gml_3848C6F8-F580-457D-9574-00EE2FBBD622" srsName="EPSG:25832">
          <gml:pos>553033.3404 5934965.6494</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_c7c478df-6f54-421d-9bec-905aca72dc9d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553068.719 5934906.724</gml:lowerCorner>
          <gml:upperCorner>553086.73 5934922.176</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_b38f687d-0607-4268-a8bf-9621055a8ad3" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_2FEF813E-9A30-4467-AF38-16E0B2ADAD91" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553077.824 5934906.916 553077.712 5934912.206 553086.713 5934912.385 
553086.73 5934916.566 553086.698 5934922.176 553077.503 5934921.986 
553077.601 5934917.426 553068.719 5934917.234 553068.953 5934906.724 
553077.824 5934906.916 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_b38f687d-0607-4268-a8bf-9621055a8ad3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553081.477 5934917.28</gml:lowerCorner>
          <gml:upperCorner>553081.477 5934917.28</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c7c478df-6f54-421d-9bec-905aca72dc9d" />
      <xplan:position>
        <gml:Point gml:id="Gml_654AAE6E-A122-4BE9-93DB-B637D75F53FB" srsName="EPSG:25832">
          <gml:pos>553081.477 5934917.28</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_6b206f9d-642e-4994-8d08-910f780396c3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553095.689 5935038.171</gml:lowerCorner>
          <gml:upperCorner>553110.462 5935069.942</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_10D3DEEA-0B74-4ED5-A97A-AACD818E2219" srsName="EPSG:25832">
          <gml:posList>553095.689 5935069.942 553110.462 5935038.171 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_7d342af4-c53d-4e71-bc76-be962c12437c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553035.989 5934957.607</gml:lowerCorner>
          <gml:upperCorner>553050.42 5934968.139</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_B9740369-50B4-4C70-ADE9-223ACC77A30D" srsName="EPSG:25832">
          <gml:posList>553035.989 5934957.607 553043.265 5934962.917 553050.42 5934968.139 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_cb0cc7f0-678b-41e8-a97c-cb974d28d57a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553171.528 5934939.116</gml:lowerCorner>
          <gml:upperCorner>553194.696 5934954.625</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_1A61F733-7C5D-45D4-BEEF-5B3FC71F7C80" srsName="EPSG:25832">
          <gml:posList>553171.528 5934939.116 553177.07 5934942.663 553177.7 5934943.066 
553184.403 5934947.356 553185.432 5934948.014 553187.507 5934949.495 
553189.882 5934951.189 553194.696 5934954.625 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_dbef154f-2649-45e1-8bac-cef02e300755">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553183.41 5934896.056</gml:lowerCorner>
          <gml:upperCorner>553192.546 5934908.48</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_B46DF082-61AB-4493-805E-636F98D14086" srsName="EPSG:25832">
          <gml:posList>553183.41 5934896.056 553192.546 5934908.48 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:SO_Denkmalschutzrecht gml:id="GML_3a42a99f-56f0-44ea-90a1-e1fc48ce5159">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553209.832 5934995.5</gml:lowerCorner>
          <gml:upperCorner>553225.674 5935035.876</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_A3D538EB-70D2-4108-970F-F3BD2DC54CA4" />
      <xplan:rechtscharakter>5000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_EDE8CDB5-FD27-4658-90C2-CE2DAFF082A9" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553220.942 5935004.5 553225.355 5935004.5 553225.48 5935016.296 
553225.674 5935034.591 553221.168 5935034.637 553221.18 5935035.815 
553214.929 5935035.876 553214.917 5935034.662 553210.12 5935034.662 
553209.946 5935016.465 553209.832 5935004.5 553214.088 5935004.5 
553214.088 5934995.5 553220.942 5934995.5 553220.942 5935004.5 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:artDerFestlegung>1000</xplan:artDerFestlegung>
    </xplan:SO_Denkmalschutzrecht>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_A3D538EB-70D2-4108-970F-F3BD2DC54CA4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553217.8348 5935010.3585</gml:lowerCorner>
          <gml:upperCorner>553217.8348 5935010.3585</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>artDerFestlegung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_3a42a99f-56f0-44ea-90a1-e1fc48ce5159" />
      <xplan:position>
        <gml:Point gml:id="Gml_5DD62320-01D8-4731-9971-2421A5BA6694" srsName="EPSG:25832">
          <gml:pos>553217.8348 5935010.3585</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_56b77369-2f25-494e-a4e3-8d9e1c71f1fe">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553058.048 5934952.2</gml:lowerCorner>
          <gml:upperCorner>553089.772 5934984.107</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D1D18052-4612-41C4-A51C-10EE4594623A" srsName="EPSG:25832">
          <gml:posList>553089.772 5934983.632 553075.059 5934984.107 553074.465 5934965.999 
553058.34 5934966.473 553058.048 5934956.553 553074.119 5934956.09 
553074.014 5934952.687 553088.705 5934952.2 553089.772 5934983.632 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_357b57be-7114-4abc-af2a-d73f1c77dbfc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553106.434 5934922.09</gml:lowerCorner>
          <gml:upperCorner>553139.99 5934939.345</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_a8cf0ab5-d0ac-4b74-8ef7-097174e0b3a0" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_67dc2e78-b355-42e0-8215-77b862e7c27a" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_A9354689-BA25-45CD-B4BB-531A9CB47644" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_E4377056-A69A-40A6-90A9-BF485D1D88E2" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553133.461 5934927.565 553139.56 5934929.513 553139.99 5934936.588 
553107.603 5934939.345 553106.434 5934934.423 553116.322 5934922.09 
553133.461 5934927.565 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_a8cf0ab5-d0ac-4b74-8ef7-097174e0b3a0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553113.416 5934934.816</gml:lowerCorner>
          <gml:upperCorner>553113.416 5934934.816</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_357b57be-7114-4abc-af2a-d73f1c77dbfc" />
      <xplan:position>
        <gml:Point gml:id="Gml_C78D23AF-3B0E-4158-B9D9-373C3013169E" srsName="EPSG:25832">
          <gml:pos>553113.416 5934934.816</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_67dc2e78-b355-42e0-8215-77b862e7c27a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553118.958 5934927.851</gml:lowerCorner>
          <gml:upperCorner>553118.958 5934927.851</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_357b57be-7114-4abc-af2a-d73f1c77dbfc" />
      <xplan:position>
        <gml:Point gml:id="Gml_419BCCA2-FCFF-4BA2-83DA-E35293185F5A" srsName="EPSG:25832">
          <gml:pos>553118.958 5934927.851</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_A9354689-BA25-45CD-B4BB-531A9CB47644">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553133.3024 5934932.201</gml:lowerCorner>
          <gml:upperCorner>553133.3024 5934932.201</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_357b57be-7114-4abc-af2a-d73f1c77dbfc" />
      <xplan:position>
        <gml:Point gml:id="Gml_39714B97-EFBE-4B83-B106-B29E3094ED59" srsName="EPSG:25832">
          <gml:pos>553133.3024 5934932.201</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_5d5fe6d3-589a-45c1-a07d-6edec526d023">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553090.672 5934966.99</gml:lowerCorner>
          <gml:upperCorner>553096.432 5934982.29</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_AA0C615D-61FC-4C92-9A64-FFB0660FEAD7" srsName="EPSG:25832">
          <gml:posList>553095.726 5934966.99 553096.432 5934980.782 553091.292 5934982.29 
553090.672 5934967.572 553095.726 5934966.99 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:SO_Strassenverkehr gml:id="GML_5dcd6491-5653-4466-ad5d-75a241fa15de">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552874.001 5934779.7367</gml:lowerCorner>
          <gml:upperCorner>553297.422 5935085.6293</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C5889060-C379-41E5-8D5A-582A6D0030CD" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553213.8368 5935081.5451 553204.9108 5935084.2318 553192.8648 5935085.6293 
553187.5621 5935085.415 553185.766 5935084.4463 553185.7661 5935082.4042 
553150.7498 5935079.1018 553111.7812 5935075.8453 553094.6831 5935073.8038 
553082.2072 5935068.9619 553070.8092 5935062.0819 553059.4542 5935052.5978 
553044.519 5935036.6119 553031.1259 5935019.6031 553014.4569 5934994.3281 
553009.0721 5934988.229 553000.9072 5934982.2861 552994.023 5934979.92 
552986.924 5934979.8108 552972.5732 5934983.3912 552948.1441 5934990.6031 
552933.903 5934994.547 552886.231 5935034.3988 552879.9168 5935039.631 
552874.001 5935034.1218 552883.0332 5935018.6352 552888.6291 5935009.3851 
552931.1082 5934955.6171 552952.0543 5934944.809 552965.2372 5934938.011 
552986.7258 5934926.9259 552987.0499 5934926.758 552988.971 5934925.5043 
553000.537 5934913.1428 553014.8672 5934897.825 553016.8663 5934896.1149 
553025.9219 5934891.6242 553061.456 5934881.3049 553078.5358 5934871.3361 
553079.1448 5934870.981 553079.8868 5934870.4579 553083.1312 5934868.1733 
553089.2422 5934863.863 553095.0098 5934859.7981 553098.2191 5934856.3621 
553113.2868 5934838.659 553114.8991 5934836.7652 553116.9569 5934834.0711 
553129.3301 5934817.8982 553133.7618 5934806.3958 553135.2258 5934802.2999 
553137.6733 5934793.0499 553139.3793 5934781.6018 553139.6975 5934779.7367 
553143.3258 5934780.301 553144.2872 5934782.0122 553145.296 5934783.8069 
553150.7331 5934794.6412 553155.0143 5934802.3039 553159.147 5934807.7382 
553162.441 5934811.4292 553168.3149 5934817.5809 553169.8623 5934819.3669 
553172.5612 5934823.0972 553174.5852 5934826.8679 553176.411 5934831.035 
553177.5899 5934834.7679 553177.5968 5934834.7898 553177.8399 5934835.5592 
553178.832 5934839.171 553179.8241 5934841.5919 553190.2829 5934839.6359 
553199.1931 5934837.8829 553205.7229 5934836.5799 553205.8598 5934836.5522 
553206.9249 5934836.3399 553205.0191 5934840.1278 553205.0058 5934840.1548 
553204.4312 5934841.2961 553201.1811 5934848.2598 553201.1736 5934848.2759 
553200.6071 5934849.4898 553197.2732 5934859.2262 553193.5279 5934867.7461 
553193.5161 5934867.7722 553194.405 5934868.7579 553198.4329 5934873.2231 
553200.2872 5934874.774 553200.303 5934874.7872 553204.8719 5934878.6078 
553212.7789 5934883.967 553212.8425 5934884.0082 553216.7411 5934886.5352 
553216.799 5934886.5729 553220.3342 5934888.8643 553222.9909 5934890.5868 
553223.3448 5934890.8159 553224.8869 5934891.8158 553228.6571 5934898.0069 
553229.5599 5934899.701 553229.601 5934899.7782 553231.1031 5934902.5978 
553231.1429 5934902.5978 553242.0078 5934917.1572 553248.8602 5934926.0078 
553255.778 5934933.7251 553259.1392 5934937.0409 553259.8742 5934936.088 
553262.6892 5934937.7119 553263.147 5934938.0008 553263.152 5934938.0039 
553264.4982 5934938.8532 553268.5681 5934941.4213 553274.334 5934945.2999 
553274.3377 5934945.3024 553279.481 5934948.762 553284.7991 5934952.453 
553291.8551 5934958.4618 553291.9377 5934958.5321 553296.3542 5934962.2929 
553297.422 5934963.64 553296.465 5934964.008 553281.477 5934952.138 
553277.975 5934949.65 553262.451 5934939.088 553259.312 5934940.966 
553251.227 5934930.652 553241.738 5934918.349 553240.721 5934917.011 
553232.334 5934905.994 553229.571 5934901.985 553225.945 5934895.043 
553225.005 5934893.858 553223.027 5934892.152 553221.753 5934891.379 
553220.926 5934890.99 553219.476 5934890.661 553217.807 5934890.502 
553216.457 5934890.731 553215.367 5934891.142 553213.947 5934892.182 
553212.496 5934893.811 553210.766 5934896.192 553206.243 5934901.709 
553197.458 5934912.244 553197.366 5934912.538 553197.095 5934912.938 
553196.604 5934912.688 553187.964 5934921.061 553182.931 5934925.566 
553178.548 5934930.234 553176.754 5934932.327 553175.469 5934933.103 
553171.528 5934939.116 553166.521 5934947.057 553161.764 5934955.9 
553161.945 5934958.374 553158.234 5934965.678 553158.834 5934975.654 
553159.066 5934979.522 553164.132 5935014.151 553164.204 5935014.645 
553164.343 5935014.935 553165.374 5935017.085 553168.862 5935026.498 
553176.261 5935047.118 553184.408 5935058.141 553190.206 5935069.693 
553209.094 5935069.728 553225.953 5935070.404 553226.5451 5935072.351 
553222.6889 5935075.3171 553213.8368 5935081.5451 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
          <gml:interior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553123.501 5935072.151 553142.762 5935074.21 553184.083 5935078.629 
553185.008 5935069.68 553185.515 5935068.051 553184.935 5935065.063 
553182.104 5935059.562 553173.71 5935048.054 553167.249 5935029.425 
553164.607 5935021.81 553161.69 5935013.361 553157.309 5934983.359 
553157.106 5934979.841 553156.437 5934975.389 553152.147 5934975.217 
553143.103 5934989.253 553137.583 5934996.006 553131.071 5935000.04 
553117.73 5935007.437 553110.551 5935013.998 553106.13 5935019.962 
553105.216 5935024.702 553107.402 5935029.38 553116.929 5935038.969 
553120.625 5935048.565 553121.441 5935055.683 553121.856 5935058.74 
553121.255 5935067.968 553123.501 5935072.151 </gml:posList>
            </gml:LinearRing>
          </gml:interior>
          <gml:interior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D3C9F33E-3E25-4C5E-B6E7-1257B48747DB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553117.52 5935066.961 553117.936 5935059.163 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C0B1F4A1-5355-408A-975B-CE31C486E0E3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553117.936 5935059.163 553116.587 5935049.381 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B1896103-B3AA-405E-80E2-0F63D5823920" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553116.587 5935049.381 553113.536 5935040.973 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8013BEA0-F779-4B4A-A121-7D480521159E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553113.536 5935040.973 553110.462 5935038.171 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8E1AC1D2-64BE-41FF-BA44-43DA2CEB3366" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553110.462 5935038.171 553103.983 5935031.706 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DD41C738-9C4E-4E4D-ABE9-BE94A83F5FC2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553103.983 5935031.706 553102.218 5935027.505 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4ECF3AB4-80CD-4981-991C-5525D6263AAB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553102.218 5935027.505 553101.45 5935025.676 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E12D0FEE-63F3-4889-825A-E2A4794ADCD9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553101.45 5935025.676 553101.477 5935025.162 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9C6E4542-8F48-4B42-9606-AF1853EEE817" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553101.477 5935025.162 553101.782 5935019.351 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C4A54394-7B56-46B9-B911-AD7B8352D6FD" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553101.782 5935019.351 553102.798 5935017.826 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7EB0CF56-0475-433E-BFE0-9C54DCECCEE4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553102.798 5935017.826 553107.768 5935011.213 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5F2719F5-F50E-42DD-8856-7E08EA5296D2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553107.768 5935011.213 553108.126 5935010.891 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_889675AE-E08D-4E42-A74B-DF961DCA6472" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553108.126 5935010.891 553115.575 5935004.177 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7B89658F-31A9-42CD-9090-4D448E90FDA8" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553115.575 5935004.177 553117.725 5935002.984 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4DC2E5DE-66B9-4BF9-9E00-30DECE903CA5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553117.725 5935002.984 553129.061 5934996.69 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4AED1B9E-8B53-4E13-B5F0-9985B3DE114A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553129.061 5934996.69 553135.473 5934992.573 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5C6D31E5-1DE3-4353-9EE3-C300E211F5A0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553135.473 5934992.573 553139.784 5934987.18 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6834F59C-4EC4-4FD1-AAF5-1BC57DB5E3F7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553139.784 5934987.18 553142.02 5934983.676 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CBD14C63-AB3E-4921-BAE1-DD69AF5C57B5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553142.02 5934983.676 553149.925 5934971.29 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_50B93AF3-1ED2-42FD-BFFC-FE42F6D3D955" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553149.925 5934971.29 553150.281 5934970.732 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AB6F0386-516A-41F7-9AE5-B639D772710E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553150.281 5934970.732 553153.509 5934962.184 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_13F16554-30FF-4CD4-A177-00A4761814EC" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553153.509 5934962.184 553156.303 5934954.786 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F962FF97-EE6F-44F7-A454-B2DF0B1CC40D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553156.303 5934954.786 553156.585 5934953.467 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CC3974DF-3E2E-4DB8-B13A-572B227EB7DD" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553156.585 5934953.467 553158.31 5934945.4 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_03FD15C9-1372-4B7D-94DB-A063647DBDEA" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553158.31 5934945.4 553157.345 5934941.6 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_97A8D1C5-950B-4577-9F52-0CE88FC3DCA7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553157.345 5934941.6 553135.533 5934943.913 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_1056B1C9-8B87-436F-B3FF-E1C021344FD2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553135.533 5934943.913 553125.302 5934944.827 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0D17503A-6098-4B39-A556-275C53A0A5F9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553125.302 5934944.827 553113.808 5934945.854 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7CD21CB0-2C7F-4236-A2DB-B45BB55C46FF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553113.808 5934945.854 553102.888 5934946.829 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AB715AAF-6846-4BD0-AD0B-6BB01F1BEBD2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553102.888 5934946.829 553089.85 5934947.994 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_64378360-4938-4F5B-933B-59B6A14219D4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553089.85 5934947.994 553082.68 5934948.635 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_03B964FD-6587-4577-AFB2-B39EE394B456" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553082.68 5934948.635 553035.989 5934957.607 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B1D99643-8440-4B63-AE14-DD725C3D0DE7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553035.989 5934957.607 553017.281 5934964.074 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7CECDBA7-1694-44D1-B65F-12413AD3A7D0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553017.281 5934964.074 552992.412 5934969.734 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D00A9EDE-CE22-484D-862A-6F4A25997F58" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552992.412 5934969.734 552992.876 5934975.696 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_42D73411-6FCA-4444-B718-1FA0F440F2EA" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">552992.876 5934975.696 552994.788164679 5934975.98703473 552996.655 5934976.493 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_AB1C681C-D98A-4592-B173-5531D59C884C" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">552996.655 5934976.493 552998.753979175 5934977.11199307 553000.805 5934977.875 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_B35B692B-4488-4DE7-8B59-A38528FC5AE7" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553000.805 5934977.875 553002.415580336 5934978.63379626 553004.008 5934979.43 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_0D417B62-D42A-4AD7-90CB-B5DAE8392FB7" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553004.008 5934979.43 553005.261069704 5934980.02704046 553006.401 5934980.819 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_4A74187E-9BC3-487D-AE72-D91B17432480" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553006.401 5934980.819 553007.206699469 5934981.38877603 553007.983 5934981.998 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_E8A21330-A2C0-4F82-B6C3-72FA8B251C72" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553007.983 5934981.998 553009.803215356 5934983.40416606 553011.626 5934984.807 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_BE0716F3-EE49-41A3-868A-741EBF750C97" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553011.626 5934984.807 553012.095874911 5934985.20788719 553012.543 5934985.634 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_56CAE6D8-5CCE-4E86-BD1A-892CE9C2A17C" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553012.543 5934985.634 553012.831989058 5934985.93598812 553013.109 5934986.249 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_9DDE7329-7575-4EAE-9189-DC54F5B24D6F" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553013.109 5934986.249 553014.366585597 5934987.49655946 553015.535 5934988.828 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_FB929EA5-1456-479D-8EB5-8114FC426061" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553015.535 5934988.828 553016.486967286 5934989.75996394 553017.322 5934990.798 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2E30F6FF-C449-467E-805A-5306EB776A17" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553017.322 5934990.798 553018.342 5934991.971 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B0CEC94D-30A9-4BD8-B874-16008403B6F5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553018.342 5934991.971 553018.771 5934992.782 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E346ACD9-FA67-4F57-940D-DB0E2198189C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553018.771 5934992.782 553019.857 5934994.632 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F8B99D66-6A3F-467B-BFE6-286C60A01CCA" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553019.857 5934994.632 553021.17 5934996.737 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AFBF10E3-9E21-4FA8-BFA4-B2177EBBB357" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553021.17 5934996.737 553023.548 5935000.455 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_97D68E6A-4AC1-4AB9-BC1D-7953CEC10A44" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553023.548 5935000.455 553024.258 5935001.607 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C8841A53-B521-4CF4-BCEE-36F5743C261C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553024.258 5935001.607 553028.178 5935007.962 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7ED06A31-0663-4718-A694-C1961DB69022" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553028.178 5935007.962 553032.5 5935013.818 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CBAF55C8-1409-4B44-934E-75C1458E1510" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553032.5 5935013.818 553034.177 5935016.092 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A0B1A368-3045-40CC-9DB8-9F7623E4868D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553034.177 5935016.092 553039.242 5935022.958 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_715D8899-C696-4E39-8C1B-8102F870F705" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553039.242 5935022.958 553041.991 5935026.6 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D3C73527-9CB9-4302-B124-A9C2A611E6CB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553041.991 5935026.6 553043.308 5935028.187 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A5A36534-32F3-42CB-81BE-42A796F33B45" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553043.308 5935028.187 553047.724 5935033.506 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2BDF39CE-7CFA-4A59-9CA2-02830FE7DE4D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553047.724 5935033.506 553048.283 5935034.119 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D878B340-2D89-4029-B8B8-BF9F212F0306" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553048.283 5935034.119 553052.79 5935039.048 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_75B99F59-872B-4F36-94B2-8EB2FCF05280" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553052.79 5935039.048 553056.895 5935043.264 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8A4C754E-9324-4488-8ABC-FF0D3294AA76" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553056.895 5935043.264 553063.478 5935049.314 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_65754417-8BDA-400E-B9AB-FF51989566C0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553063.478 5935049.314 553066.392 5935052.049 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_1B43BBB7-A3A6-438C-95F7-1A7835C91DC6" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553066.392 5935052.049 553076.067 5935060.047 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8A8F5B14-243D-4A49-81AC-DCE1126CE343" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553076.067 5935060.047 553078.363 5935061.752 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_54CDE7CA-2E78-4165-BD1D-53F9AD1FEAEF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553078.363 5935061.752 553082.896 5935064.915 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C832B6E0-5C46-4786-BC61-325A6B33AF0C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553082.896 5935064.915 553083.305 5935065.342 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D63A436A-E02D-4ABE-BCB1-4443B128F473" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553083.305 5935065.342 553084.065 5935065.746 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DAB05855-72B9-4A6C-ABF9-98B9E842B8C1" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553084.065 5935065.746 553086.919 5935066.977 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_00DFE150-212B-4F68-A6B1-8037E3F24C94" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553086.919 5935066.977 553092.331 5935069.18 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F84E0D0A-E042-4E22-868F-1FF03E7F91C2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553092.331 5935069.18 553095.689 5935069.942 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F440B86F-33F3-45C5-9C3F-F1CA434429AC" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553095.689 5935069.942 553097.811 5935070.423 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C717C1AF-55F2-4A9B-A772-FD2301218A9D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553097.811 5935070.423 553103.762 5935071.226 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9E4CAFCD-F38E-4A7A-B726-C23C17F6F2A0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553103.762 5935071.226 553105.475 5935071.436 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_787067D2-DE17-47B3-BF4B-E57C8C25F7B6" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553105.475 5935071.436 553110.286565974 5935068.68207358 553115.737 5935069.696 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C31A26C8-259C-4476-9E5B-146BC750643D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553115.737 5935069.696 553117.52 5935066.961 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:interior>
          <gml:interior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552959.196 5934967.009 552972.303 5934962.412 552975.051 5934961.949 
552990.765 5934959.302 552991.593 5934959.206 552995.787 5934958.72 
553002 5934957.999 553009.011 5934956.383 553013.647 5934954.98 
553016.806 5934953.97 553020.569 5934952.53 553026.211 5934950.835 
553031.662 5934949.196 553041.055 5934946.494 553049.298 5934944.855 
553065.873 5934941.559 553080.833 5934938.839 553097.292 5934938.869 
553100.164 5934937.073 553103.012 5934935.292 553105.81 5934933.542 
553115.523 5934920.749 553122.71 5934908.025 553123.543 5934906.318 
553124.216 5934904.527 553115.871 5934912.111 553114.142 5934913.007 
553110.629 5934914.538 553102.403 5934918.623 553101.537 5934917.698 
553110.135 5934913.501 553113.658 5934912.004 553114.928 5934911.316 
553122.427 5934903.263 553126.851 5934897.155 553132.193 5934889.781 
553140.787 5934879.847 553141.873 5934878.592 553149.943 5934865.779 
553152.349 5934861.958 553156.169 5934856.994 553156.586 5934856.672 
553164.913 5934850.241 553170.138 5934846.811 553175.517 5934842.158 
553176.759 5934840.637 553177.11 5934837.379 553177.284 5934835.532 
553173.229 5934825.976 553172.098 5934823.996 553171.993 5934823.814 
553169.858 5934820.95 553167.562 5934818.16 553161.502 5934811.869 
553155.594 5934804.929 553154.396 5934802.93 553153.371 5934801.55 
553151.82 5934798.792 553150.043 5934795.408 553144.105 5934783.032 
553143.428 5934781.621 553140.162 5934783.671 553138.955 5934791.537 
553136.295 5934802.084 553134.486 5934808.353 553131.412 5934817.635 
553122.243 5934829.63 553117.248 5934835.864 553109.856 5934845.087 
553106.117 5934849.751 553102.972 5934853.676 553095.905 5934861.528 
553093.69 5934863.988 553081.41 5934873.453 553067.516 5934881.336 
553063.87 5934883.291 553062.516 5934884.016 553038.198 5934891.021 
553028.056 5934893.921 553022.536 5934896.272 553017.992 5934898.206 
553010.063 5934906.446 552989.494 5934927.822 552967.852 5934938.957 
552968.262 5934939.851 552966.031 5934941.015 552938.442 5934955.399 
552939.785 5934956.809 552943.061 5934960.243 552943.139 5934960.325 
552952.682 5934970.191 552959.196 5934967.009 </gml:posList>
            </gml:LinearRing>
          </gml:interior>
          <gml:interior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5260F2F1-0C60-4566-A8C3-A4BDDBC225AF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553107.603 5934939.345 553139.99 5934936.588 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A0D133B1-B197-40F2-9193-7918E6745EC4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553139.99 5934936.588 553153.416 5934935.409 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_57573E00-4962-4B8D-9A73-659708372C68" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553153.416 5934935.409 553156.212 5934932.322 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E69D558F-5FD8-40B0-A306-36E445880FDF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553156.212 5934932.322 553157.142 5934922.128 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8C726E15-171A-46E4-8D92-E91BC79CD366" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553157.142 5934922.128 553157.916 5934920.143 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_75C20BE0-DEBF-40BC-92A7-7D0B44F73B10" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553157.916 5934920.143 553158.107 5934919.517 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_971C0788-94B3-4355-A60A-5F7B76D25D7B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553158.107 5934919.517 553160.29 5934917.59 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_FC1DA273-EFCA-4D16-A5CE-BB298BFF3E7F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553160.29 5934917.59 553168.888 5934909.251 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_10C85A32-BF48-4395-AE2B-8EDBDBAED4C2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553168.888 5934909.251 553175.612 5934902.116 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_13BA2D15-3DF3-434F-9230-161CC6CF9C6C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553175.612 5934902.116 553177.597 5934900.035 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_1127D11D-7927-4B93-856E-A932DEE76FB3" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553177.597 5934900.035 553181.305105664 5934893.70674587 553183.177 5934886.615 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_63781619-92FA-4604-BE40-47F8BEF8EB72" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.177 5934886.615 553183.625 5934883.814 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_43BE9615-6C7C-4CCE-B436-F7AE7786E9B3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.625 5934883.814 553185.402 5934881.885 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_0B162D71-8A21-4A28-9E5D-8EAC5AF0D555" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553185.402 5934881.885 553188.92910789 5934874.81577452 553192.221 5934867.634 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_46E80E0F-11E3-431A-BE89-1665AA569CDF" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553192.221 5934867.634 553192.743060412 5934866.49586231 553193.227 5934865.341 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_41565D57-2CD9-4703-A3B1-77F35778F624" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553193.227 5934865.341 553194.323873055 5934862.71730787 553195.395 5934860.083 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6700F5C9-5580-45BF-8BBD-5561E226508F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553195.395 5934860.083 553196.537 5934856.221 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_B424BF06-7C6D-40E4-886F-9CC91B44706A" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553196.537 5934856.221 553199.739943437 5934848.44813307 553203.114 5934840.748 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8B3B9F17-D208-4653-9071-F5BD901AE757" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553203.114 5934840.748 553203.035 5934840.252 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5FCE0DA2-0EBF-420D-BD50-C8579D7E5E32" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553203.035 5934840.252 553202.629 5934839.931 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A4632E64-007D-4A87-BA32-829FAB3D5584" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553202.629 5934839.931 553201.016 5934838.666 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3B374D00-C155-4051-88D4-6D26C010B37B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553201.016 5934838.666 553200.412 5934838.509 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_281090AD-C2F0-47B2-A9C0-5C1BC5F4887D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553200.412 5934838.509 553199.801 5934838.653 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CB21F7E3-D315-4A72-A2FD-2E30DEEF3C5C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553199.801 5934838.653 553190.987 5934840.533 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_10FA8745-E753-4118-9EBF-6D6463806D99" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553190.987 5934840.533 553184.536 5934841.337 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5CEF8D46-0B02-4338-9486-34AFD06FEAF2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553184.536 5934841.337 553182.22 5934842.051 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4FE7BF57-5EDC-4D5C-9004-32B305F7D60F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553182.22 5934842.051 553181.173 5934842.468 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_07A2D776-F1A4-483A-BA6E-4201CD6F28C0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553181.173 5934842.468 553180.481 5934842.873 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_268DA127-6CD8-4E0C-9F0F-5D55B7BF8827" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553180.481 5934842.873 553180.775 5934843.278 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AB9B418F-191F-4B71-AE8A-48A59356FDDB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553180.775 5934843.278 553172.603 5934848.851 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5A716AA0-7498-4BA6-B539-6BE74BCDFFC3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553172.603 5934848.851 553158.242 5934860.856 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A30102E3-7E88-483C-9EB1-0E23C254DF35" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553158.242 5934860.856 553156.849 5934862.162 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3529B34B-A4E1-4B44-ABD9-1337342FE9A3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553156.849 5934862.162 553151.924 5934869.374 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3BC50318-35C7-47FD-AC37-ED52E4B83946" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553151.924 5934869.374 553150.315 5934871.729 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0E2D14C7-848B-42B8-B1BE-3924D4EBB94A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553150.315 5934871.729 553144.36 5934880.367 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B35702EC-71A5-4A80-8EBF-ADCED19E4433" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553144.36 5934880.367 553137.562 5934887.517 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_65AB127E-79A1-4CDB-A106-B9AAA2A71BF9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553137.562 5934887.517 553136.66 5934888.715 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A2593100-74B0-4DF5-BB71-A4221130282C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553136.66 5934888.715 553131.91 5934895.013 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3C6D874C-4ECB-4CE8-8A35-8E42A2DA45FE" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553131.91 5934895.013 553128.423 5934900.626 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_FB20C9AA-4D98-4C4E-902B-5B78B36A895E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553128.423 5934900.626 553127.846 5934900.326 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EC72B6DB-0046-49B3-A7C2-CAE8FA92EF95" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553127.846 5934900.326 553127.577 5934900.81 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8F93B607-790E-4C08-B1E4-02B1443DB51B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553127.577 5934900.81 553120.259 5934915.422 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D325949E-27F2-41BC-8214-6CD3E7D584EF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553120.259 5934915.422 553116.322 5934922.09 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CA799AD7-CF78-4B0F-9ED7-DB109503E513" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553116.322 5934922.09 553106.434 5934934.423 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B37169FA-235A-47B4-A9B4-4EA891A5688A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553106.434 5934934.423 553107.603 5934939.345 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:interior>
          <gml:interior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_12EECCF9-EBF0-40E7-920B-3B5C7CBA0436" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.234 5934874.11 553187.582 5934883.144 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_93D39549-2587-4D19-8948-B422630DB387" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553187.582 5934883.144 553185.877 5934886.896 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_279E0D46-3EE0-49A7-8E23-2566B8AE3BD7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553185.877 5934886.896 553183.41 5934896.056 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CFACB5D8-80CC-4848-8C46-A06FC8324891" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.41 5934896.056 553179.316 5934902.227 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_007E0E07-B4B8-4A68-89B2-33ABF8A2848F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553179.316 5934902.227 553171.7 5934910.53 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_08126770-8F0C-48CD-ACFD-E9B0201AFB2C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553171.7 5934910.53 553171.133 5934911.148 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_648099CF-967E-4CA9-8C78-1E099C4A8B54" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553171.133 5934911.148 553167.986 5934914.118 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_07AB4DE2-B6C9-448C-B9A6-3186A74BF03D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553167.986 5934914.118 553164.182 5934917.711 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9C805E9F-F0FD-438E-AE1C-367AF38BD638" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553164.182 5934917.711 553160.908 5934920.802 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C60F2A43-01EF-4EA7-A507-FB46F645C998" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553160.908 5934920.802 553160.454 5934921.468 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_39E3D7E9-728A-436C-BF9A-31A74669342C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553160.454 5934921.468 553159.894 5934928.518 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A73CD00A-C1CC-49F8-A7BA-20A7E1CB1A85" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553159.894 5934928.518 553160.51 5934932.7 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_78AECBC5-B78E-4B16-8F7F-E398669898F2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553160.51 5934932.7 553162.208 5934934.153 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F1140D1B-962B-4344-B0CC-FE76E39511E9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553162.208 5934934.153 553164.759 5934933.554 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_ECBE5661-CE44-4CFF-B671-9C30DAAC38F9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553164.759 5934933.554 553167.982 5934931.983 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F60FB05B-1795-4762-B742-1F8AB4679265" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553167.982 5934931.983 553172.659 5934928.499 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B27A4411-4138-4D3E-8EDC-4191852E1880" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553172.659 5934928.499 553173.742 5934927.691 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DBD777EA-F369-4E61-9A2B-42CA78AE5C2B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553173.742 5934927.691 553176.762 5934924.672 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_057BDF58-119E-4D2D-ACBD-55D640CF72B9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553176.762 5934924.672 553180.645 5934920.789 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6DCCD644-1A14-4AD1-A0AD-F827D017CEBE" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553180.645 5934920.789 553181.662 5934919.772 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_35E09797-BF88-4A88-BD0F-B797A86A9186" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553181.662 5934919.772 553192.546 5934908.48 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_796042EC-DF0E-4AAD-AF6F-C73F5B40B48A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.546 5934908.48 553192.797 5934908.601 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_1C35F9A3-B8C9-4B02-A5C8-E8CB0411CA97" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553192.797 5934908.601 553199.046989233 5934901.73301248 553204.931 5934894.549 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7A6A3099-A776-48AE-91E9-D30D8619AB21" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553204.931 5934894.549 553211.811 5934884.804 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_37371E19-2C0F-45C9-992E-B463469F8F6D" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553211.811 5934884.804 553203.957362031 5934878.60729685 553196.342 5934872.12 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E27A328A-7D18-4C2F-992B-82452552D129" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553196.342 5934872.12 553194.051 5934869.626 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B4BE5CFF-DB7B-40EC-BCA5-B584DC1EF025" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553194.051 5934869.626 553192.234 5934874.11 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:interior>
          <gml:interior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552934.511 5934957.536 552907.864 5934989.746 552887.269 5935017.351 
552886.39 5935018.797 552878.613 5935031.589 552881.056 5935034.204 
552894.064 5935023.516 552908.181 5935011.64 552930.917 5934992.515 
552951.709 5934976.337 552951.234 5934974.356 552934.511 5934957.536 
</gml:posList>
            </gml:LinearRing>
          </gml:interior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:nutzungsform>2000</xplan:nutzungsform>
      <xplan:hatDarstellungMitBesondZweckbest>false</xplan:hatDarstellungMitBesondZweckbest>
    </xplan:SO_Strassenverkehr>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_0099d02c-ea9f-4b35-9cc1-b80b945b5593">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553146.487 5935053.345</gml:lowerCorner>
          <gml:upperCorner>553160.168 5935067.994</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_e9c4a430-9fc5-42c1-a786-3c47233b412a" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C34158E1-3DEA-43E0-AB53-CECFE7E406F6" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553160.013 5935054.772 553159.342 5935060.578 553160.168 5935060.673 
553159.297 5935067.994 553146.487 5935066.471 553148.26 5935053.345 
553160.013 5935054.772 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_e9c4a430-9fc5-42c1-a786-3c47233b412a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553153.343 5935060.659</gml:lowerCorner>
          <gml:upperCorner>553153.343 5935060.659</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0099d02c-ea9f-4b35-9cc1-b80b945b5593" />
      <xplan:position>
        <gml:Point gml:id="Gml_988B4CE6-4451-4E6A-98DD-073758155458" srsName="EPSG:25832">
          <gml:pos>553153.343 5935060.659</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_7657b8de-b5d6-48f0-834a-c7ec8b5a10ac">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552883.311 5935021.549</gml:lowerCorner>
          <gml:upperCorner>552888.66 5935027.056</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_0C3E54A1-F7F9-4AC6-913C-491BFFAE95D6" srsName="EPSG:25832">
          <gml:posList>552886.373 5935027.056 552883.311 5935025.064 552885.6 5935021.549 
552888.66 5935023.541 552886.373 5935027.056 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_9cddeaff-df57-49d7-bfd0-f03e3ef2d4ab">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552938.442 5934781.621</gml:lowerCorner>
          <gml:upperCorner>553177.284 5934970.191</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_83278A32-ECA0-4E2A-9026-F53CB39973F8" srsName="EPSG:25832">
          <gml:posList>552972.303 5934962.412 552975.051 5934961.949 552990.765 5934959.302 
552995.787 5934958.72 553002 5934957.999 553009.011 5934956.383 
553013.647 5934954.98 553016.806 5934953.97 553020.569 5934952.53 
553026.211 5934950.835 553031.662 5934949.196 553041.055 5934946.494 
553049.298 5934944.855 553065.873 5934941.559 553080.833 5934938.839 
553097.292 5934938.869 553100.164 5934937.073 553103.012 5934935.292 
553105.81 5934933.542 553115.523 5934920.749 553122.71 5934908.025 
553123.543 5934906.318 553124.216 5934904.527 553115.871 5934912.111 
553114.142 5934913.007 553110.629 5934914.538 553102.403 5934918.623 
553101.537 5934917.698 553110.135 5934913.501 553113.658 5934912.004 
553114.928 5934911.316 553122.427 5934903.263 553126.851 5934897.155 
553132.193 5934889.781 553140.787 5934879.847 553141.873 5934878.592 
553149.943 5934865.779 553152.349 5934861.958 553156.169 5934856.994 
553156.586 5934856.672 553164.913 5934850.241 553170.138 5934846.811 
553175.517 5934842.158 553176.759 5934840.637 553177.11 5934837.379 
553177.284 5934835.532 553173.229 5934825.976 553172.098 5934823.996 
553171.993 5934823.814 553169.858 5934820.95 553167.562 5934818.16 
553161.502 5934811.869 553155.594 5934804.929 553154.396 5934802.93 
553153.371 5934801.55 553151.82 5934798.792 553150.043 5934795.408 
553144.105 5934783.032 553143.428 5934781.621 553140.162 5934783.671 
553138.955 5934791.537 553136.295 5934802.084 553134.486 5934808.353 
553131.412 5934817.635 553122.243 5934829.63 553117.248 5934835.864 
553109.856 5934845.087 553106.117 5934849.751 553102.972 5934853.676 
553095.905 5934861.528 553093.69 5934863.988 553081.41 5934873.453 
553067.516 5934881.336 553063.87 5934883.291 553062.516 5934884.016 
553038.198 5934891.021 553028.056 5934893.921 553022.536 5934896.272 
553017.992 5934898.206 553010.063 5934906.446 552989.494 5934927.822 
552967.852 5934938.957 552968.262 5934939.851 552966.031 5934941.015 
552938.442 5934955.399 552939.785 5934956.809 552943.061 5934960.243 
552943.139 5934960.325 552952.682 5934970.191 552959.196 5934967.009 
552972.303 5934962.412 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_f9adc0fa-d2db-484d-9f3b-e541c0b713e5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553130.235 5934859.725</gml:lowerCorner>
          <gml:upperCorner>553144.568 5934873.201</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_fb5b0473-1a22-4340-b746-7fb5f4fc3973" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0261F468-9D4E-4477-912E-49AEE7E53E5D" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553144.568 5934865.934 553139.997 5934873.201 553130.235 5934867.061 
553134.851 5934859.725 553144.568 5934865.934 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_fb5b0473-1a22-4340-b746-7fb5f4fc3973">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553136.881 5934866.142</gml:lowerCorner>
          <gml:upperCorner>553136.881 5934866.142</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_f9adc0fa-d2db-484d-9f3b-e541c0b713e5" />
      <xplan:position>
        <gml:Point gml:id="Gml_A166C87A-FDFC-492E-A057-7B9FDC35D1F2" srsName="EPSG:25832">
          <gml:pos>553136.881 5934866.142</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_41bba10d-324d-4b86-953f-fac179070f1e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553108.223 5935015.277</gml:lowerCorner>
          <gml:upperCorner>553134.584 5935037.627</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_4495FCFF-6473-4C4A-957E-DF11ABF06061" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553134.584 5935025.709 553128.441 5935037.627 553124.052 5935035.315 
553124.67 5935034.14 553108.223 5935025.487 553109.696 5935022.669 
553108.842 5935022.223 553110.606 5935018.835 553112.106 5935019.615 
553114.354 5935015.277 553134.584 5935025.709 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_7f31863d-6010-4a7c-a4e2-25f53f827ea9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552918.107 5934978.538</gml:lowerCorner>
          <gml:upperCorner>552934.286 5934994.296</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_59069E1F-2FBF-4FE3-8B63-0B4F71BE7A44" srsName="EPSG:25832">
          <gml:posList>552934.286 5934986.632 552924.796 5934994.296 552918.107 5934986.014 
552927.372 5934978.538 552934.286 5934986.632 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_b51cde7a-a482-45b0-b85b-dfcb5cf488a5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553141.059 5934795.489</gml:lowerCorner>
          <gml:upperCorner>553150.589 5934808.362</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_885989CF-A4F7-4A9A-8EE6-1E724E4CA278" srsName="EPSG:25832">
          <gml:posList>553141.059 5934797.578 553146.248 5934795.489 553150.589 5934806.271 
553145.394 5934808.362 553141.059 5934797.578 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_acb722ac-5885-492e-9e5c-9de2388498cb">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553121.441 5935015.808</gml:lowerCorner>
          <gml:upperCorner>553151.158 5935074.21</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_65EFB9ED-3CF8-4027-8262-F6D1100D2E5E" srsName="EPSG:25832">
          <gml:posList>553121.441 5935055.683 553137.515 5935025.234 553143.91 5935015.808 
553150.719 5935024.482 553151.158 5935029.663 553147.609 5935029.715 
553142.762 5935074.21 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_ce62a073-cf15-40b0-b3c0-b08ddb2eaf4c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553126.405 5935050.175</gml:lowerCorner>
          <gml:upperCorner>553142.444 5935066.249</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_79C5C28E-EFCB-4970-B2CE-F77CD85D03D0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553142.444 5935051.673 553140.636 5935066.249 553126.405 5935064.753 
553127.895 5935050.175 553142.444 5935051.673 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_1a037d7e-8fdf-4d9c-8f3a-c05ad6bedcb8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553105.216 5934975.217</gml:lowerCorner>
          <gml:upperCorner>553185.515 5935078.629</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_79D3C003-DAA5-4D8D-A17A-1E008EC3C8BB" srsName="EPSG:25832">
          <gml:posList>553106.13 5935019.962 553105.216 5935024.702 553107.402 5935029.38 
553116.929 5935038.969 553120.625 5935048.565 553121.441 5935055.683 
553121.856 5935058.74 553121.255 5935067.968 553123.501 5935072.151 
553142.762 5935074.21 553184.083 5935078.629 553185.008 5935069.68 
553185.515 5935068.051 553184.935 5935065.063 553182.104 5935059.562 
553173.71 5935048.054 553167.249 5935029.425 553164.607 5935021.81 
553161.69 5935013.361 553157.309 5934983.359 553157.106 5934979.841 
553156.437 5934975.389 553152.147 5934975.217 553143.103 5934989.253 
553137.583 5934996.006 553131.071 5935000.04 553117.73 5935007.437 
553110.551 5935013.998 553106.13 5935019.962 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_d81a5d6f-ca45-4fb9-a3d0-cb2312b39072">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553059.716 5935023.902</gml:lowerCorner>
          <gml:upperCorner>553077.596 5935041.91</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_980C4466-4477-449A-B1B1-57B1A65187F1" srsName="EPSG:25832">
          <gml:posList>553067.607 5935041.91 553059.716 5935034.407 553069.71 5935023.902 
553077.596 5935031.4 553067.607 5935041.91 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_dd8213ff-fe5f-42ad-9f8e-7fe102232567">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553201.747 5934907.922</gml:lowerCorner>
          <gml:upperCorner>553213.21 5934919.581</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_7ABAF80E-84EB-4E72-9A1D-3E6EE44AA2D2" srsName="EPSG:25832">
          <gml:posList>553207.372 5934919.581 553201.747 5934915.215 553207.62 5934907.922 
553213.21 5934912.26 553207.372 5934919.581 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_c2bb0da0-c6e1-4b54-ad54-f27fa4cbb721">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553184.268 5934889.037</gml:lowerCorner>
          <gml:upperCorner>553194.849 5934899.791</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_14C0ADFC-D1A2-4196-91C2-B0E016975957" srsName="EPSG:25832">
          <gml:posList>553184.268 5934895.468 553189.051 5934889.037 553194.849 5934893.378 
553190.081 5934899.791 553184.268 5934895.468 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_54ea0386-f17e-495a-8e77-b00d9e8f33b5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553048.283 5935001.594</gml:lowerCorner>
          <gml:upperCorner>553102.218 5935061.752</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_07919210-4369-4DB8-8A2B-B5B6E489D557" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_96900436-A4FF-4641-822F-244345AC2EA2" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_8471A0D0-8C82-479C-A8FA-39A3C9D03962" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_B012D118-AB54-4C22-AAD2-B50D7D9DC674" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C407BDA9-6DF5-4590-9A94-396E8288705B" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553084.786 5935005.613 553094.506 5935016.995 553101.477 5935025.162 
553101.45 5935025.676 553102.218 5935027.505 553086.17 5935050.542 
553082.877 5935055.27 553078.363 5935061.752 553076.067 5935060.047 
553066.392 5935052.049 553063.478 5935049.314 553056.895 5935043.264 
553052.79 5935039.048 553048.283 5935034.119 553081.048 5935001.594 
553084.786 5935005.613 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>3</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_07919210-4369-4DB8-8A2B-B5B6E489D557">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553087.4875 5935032.3113</gml:lowerCorner>
          <gml:upperCorner>553087.4875 5935032.3113</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_54ea0386-f17e-495a-8e77-b00d9e8f33b5" />
      <xplan:position>
        <gml:Point gml:id="Gml_8689CF84-3A3B-4B0C-B018-71BC96E436A0" srsName="EPSG:25832">
          <gml:pos>553087.4875 5935032.3113</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_96900436-A4FF-4641-822F-244345AC2EA2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553085.4766 5935027.1254</gml:lowerCorner>
          <gml:upperCorner>553085.4766 5935027.1254</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_54ea0386-f17e-495a-8e77-b00d9e8f33b5" />
      <xplan:position>
        <gml:Point gml:id="Gml_37E94BA1-9A7B-4C91-80C4-B5194F16DEA9" srsName="EPSG:25832">
          <gml:pos>553085.4766 5935027.1254</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_8471A0D0-8C82-479C-A8FA-39A3C9D03962">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553084.2066 5935021.0929</gml:lowerCorner>
          <gml:upperCorner>553084.2066 5935021.0929</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_54ea0386-f17e-495a-8e77-b00d9e8f33b5" />
      <xplan:position>
        <gml:Point gml:id="Gml_D11622AF-2488-428A-8100-B0E596421458" srsName="EPSG:25832">
          <gml:pos>553084.2066 5935021.0929</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_B012D118-AB54-4C22-AAD2-B50D7D9DC674">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553080.6083 5935014.0021</gml:lowerCorner>
          <gml:upperCorner>553080.6083 5935014.0021</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_54ea0386-f17e-495a-8e77-b00d9e8f33b5" />
      <xplan:position>
        <gml:Point gml:id="Gml_817788EE-EBED-4921-AB0C-D3B5B289E177" srsName="EPSG:25832">
          <gml:pos>553080.6083 5935014.0021</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_643c4550-2e95-4b09-9102-f35090fff58e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553271.471 5934949.65</gml:lowerCorner>
          <gml:upperCorner>553302.95 5934978.967</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_5b340fcd-e9b6-4856-81d2-262498dfc4ef" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_25e73a2a-f476-4eb2-93d3-d85c66f0d3ed" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_517AD821-E3DA-45EB-8E93-35FC156ADF54" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_7D1BCD74-9CED-4846-8035-88CAE929D11E" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_182608DB-626A-4AA4-8E9E-6E6DC651BF7C" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553302.95 5934970.42 553289.676 5934978.967 553271.471 5934954.505 
553277.975 5934949.65 553281.477 5934952.138 553296.465 5934964.008 
553297.422 5934963.64 553302.95 5934970.42 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_5b340fcd-e9b6-4856-81d2-262498dfc4ef">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553289.5094 5934966.7224</gml:lowerCorner>
          <gml:upperCorner>553289.5094 5934966.7224</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_643c4550-2e95-4b09-9102-f35090fff58e" />
      <xplan:position>
        <gml:Point gml:id="Gml_8DDCFCDE-FEF2-4C28-AE9C-EE40A7C595DB" srsName="EPSG:25832">
          <gml:pos>553289.5094 5934966.7224</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_25e73a2a-f476-4eb2-93d3-d85c66f0d3ed">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553292.2428 5934972.4283</gml:lowerCorner>
          <gml:upperCorner>553292.2428 5934972.4283</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_643c4550-2e95-4b09-9102-f35090fff58e" />
      <xplan:position>
        <gml:Point gml:id="Gml_21BF703D-8332-429B-975C-30B99397CD13" srsName="EPSG:25832">
          <gml:pos>553292.2428 5934972.4283</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_517AD821-E3DA-45EB-8E93-35FC156ADF54">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553276.1775 5934953.6792</gml:lowerCorner>
          <gml:upperCorner>553276.1775 5934953.6792</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_643c4550-2e95-4b09-9102-f35090fff58e" />
      <xplan:position>
        <gml:Point gml:id="Gml_8B37FDE9-F567-405E-A421-19A9EED0FF7B" srsName="EPSG:25832">
          <gml:pos>553276.1775 5934953.6792</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_7D1BCD74-9CED-4846-8035-88CAE929D11E">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553281.2045 5934959.5</gml:lowerCorner>
          <gml:upperCorner>553281.2045 5934959.5</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_643c4550-2e95-4b09-9102-f35090fff58e" />
      <xplan:position>
        <gml:Point gml:id="Gml_AD05B4BA-E90D-401A-84DA-D97A2F1CB82F" srsName="EPSG:25832">
          <gml:pos>553281.2045 5934959.5</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_a140ed1c-048a-4d38-925a-e87902ce91d0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553184.306 5934926.641</gml:lowerCorner>
          <gml:upperCorner>553196.787 5934939.221</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D9E5FF74-27BE-4D2A-A73E-218FD8B22E96" srsName="EPSG:25832">
          <gml:posList>553190.37 5934939.221 553184.306 5934933.74 553190.72 5934926.641 
553196.787 5934932.158 553190.37 5934939.221 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_4ca345b0-df0e-405a-acac-8b9339714a23">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553058.048 5934952.2</gml:lowerCorner>
          <gml:upperCorner>553089.772 5934984.107</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_3c72d4ab-e5ca-4e0b-b19f-a08b2987f4d2" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_769AD347-D59E-4ACF-BEA2-16D5A30F9043" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553089.772 5934983.632 553075.059 5934984.107 553074.465 5934965.999 
553058.34 5934966.473 553058.048 5934956.553 553074.119 5934956.09 
553074.014 5934952.687 553088.705 5934952.2 553089.772 5934983.632 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_3c72d4ab-e5ca-4e0b-b19f-a08b2987f4d2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553081.6 5934966.024</gml:lowerCorner>
          <gml:upperCorner>553081.6 5934966.024</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4ca345b0-df0e-405a-acac-8b9339714a23" />
      <xplan:position>
        <gml:Point gml:id="Gml_D7E9DED6-F03A-41C5-A5D3-5BAD8D7B455A" srsName="EPSG:25832">
          <gml:pos>553081.6 5934966.024</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_f2dcf196-944c-4380-9437-2b4bc283c76f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553236.647 5934939.243</gml:lowerCorner>
          <gml:upperCorner>553269.106 5934973.379</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_F03E64A7-7D8A-4BA6-A775-36D8580671C7" srsName="EPSG:25832">
          <gml:posList>553264.378 5934964.91 553269.106 5934967.848 553265.704 5934973.379 
553260.284 5934970.153 553260.688 5934969.288 553255.844 5934966.062 
553252.13 5934965.027 553245.202 5934961.035 553236.647 5934950.762 
553250.479 5934939.243 553255.315 5934945.051 553246.975 5934951.996 
553247.8 5934953.299 553255.685 5934957.842 553260.915 5934949.337 
553267.488 5934953.543 553265.989 5934956.481 553268.584 5934958.094 
553264.378 5934964.91 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_59f8b00f-304a-4e0e-8373-8ab3d92c0ddb">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552968.201 5934934.949</gml:lowerCorner>
          <gml:upperCorner>552989.421 5934953.257</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_ACB7260B-3FC8-4E37-9AAD-25E6AF6B76C0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552973.711 5934953.257 552968.201 5934942.944 552973.484 5934940.131 
552983.219 5934934.949 552985.506 5934939.234 552989.421 5934946.569 
552979.795 5934951.712 552979.087 5934950.387 552973.711 5934953.257 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_3b5686be-be43-40e1-94be-fdb47f41a0e3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.309 5934922.907</gml:lowerCorner>
          <gml:upperCorner>553153.766 5934931.949</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_B00286A3-7F45-4A17-81F2-A3A1968AB43A" srsName="EPSG:25832">
          <gml:posList>553153.294 5934931.949 553143.309 5934931.394 553143.781 5934922.907 
553153.766 5934923.462 553153.294 5934931.949 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_7436bb17-6b66-4d81-971c-53a60615019a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552996.101 5934938.648</gml:lowerCorner>
          <gml:upperCorner>553011.233 5934949.324</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_326F620D-81DD-4A9B-A324-C8B456E48D10" srsName="EPSG:25832">
          <gml:posList>553011.233 5934944.058 552999.207 5934949.324 552996.101 5934942.236 
553002.185 5934939.574 553002.92 5934941.249 553008.862 5934938.648 
553011.233 5934944.058 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_VerEntsorgung gml:id="GML_a0db7928-ba50-4e60-817b-b99cb0ce2932">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552878.613 5935018.797</gml:lowerCorner>
          <gml:upperCorner>552894.064 5935034.204</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_c18f0601-089b-4078-a2a7-9257543b1b4b" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_39B25745-A191-4860-B781-930BADC206F0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552894.064 5935023.516 552881.056 5935034.204 552878.613 5935031.589 
552886.39 5935018.797 552894.064 5935023.516 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>      
      <xplan:zweckbestimmung>
        <xplan:BP_KomplexeZweckbestVerEntsorgung>
          <xplan:allgemein>1000</xplan:allgemein>
          <xplan:textlicheErgaenzung>Abspannwerk</xplan:textlicheErgaenzung>
        </xplan:BP_KomplexeZweckbestVerEntsorgung>
      </xplan:zweckbestimmung>     
      <xplan:zugunstenVon>(HEW)</xplan:zugunstenVon>
    </xplan:BP_VerEntsorgung>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_c18f0601-089b-4078-a2a7-9257543b1b4b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552889.097 5935026.37</gml:lowerCorner>
          <gml:upperCorner>552889.097 5935026.37</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_16.01</xplan:stylesheetId>
      <xplan:art>zugunstenVon</xplan:art>
      <xplan:art>textlicheErgaenzung</xplan:art>      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_a0db7928-ba50-4e60-817b-b99cb0ce2932" />
      <xplan:position>
        <gml:Point gml:id="Gml_C26E6F1B-FCAF-4131-94FA-D0FB69775180" srsName="EPSG:25832">
          <gml:pos>552889.097 5935026.37</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_7d73c1ad-5988-426a-9615-686112ef6ad9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553158.834 5934973.847</gml:lowerCorner>
          <gml:upperCorner>553173.912 5935017.085</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_aa25e2a1-d386-40b1-a79b-405bf2a8cce9" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_40525bd4-8008-4441-bfe7-42917becd2d6" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_CD54191D-1378-4B88-8607-210D44A08E4F" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_1B982A82-6C0A-4E7E-88CD-B579DD4ECCF3" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_6596FBFE-A783-4AFC-A0D0-EEDC5187EB0B" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553168.921 5934975.398 553169.074 5934978.061 553169.567 5934985.588 
553173.912 5935015.05 553165.374 5935017.085 553164.343 5935014.935 
553164.204 5935014.645 553164.132 5935014.151 553159.066 5934979.522 
553158.834 5934975.654 553168.863 5934973.847 553168.921 5934975.398 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>5</xplan:MaxZahlWohnungen>
      <xplan:GR uom="m2">300</xplan:GR>
      <xplan:Z>1</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_aa25e2a1-d386-40b1-a79b-405bf2a8cce9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553168.362 5935009.213</gml:lowerCorner>
          <gml:upperCorner>553168.362 5935009.213</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.03</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7d73c1ad-5988-426a-9615-686112ef6ad9" />
      <xplan:position>
        <gml:Point gml:id="Gml_AC5324C4-27DA-47FE-B82F-BB9E83A07948" srsName="EPSG:25832">
          <gml:pos>553168.362 5935009.213</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_40525bd4-8008-4441-bfe7-42917becd2d6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553167.784 5935003.046</gml:lowerCorner>
          <gml:upperCorner>553167.784 5935003.046</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7d73c1ad-5988-426a-9615-686112ef6ad9" />
      <xplan:position>
        <gml:Point gml:id="Gml_51F603B7-8548-4603-B392-478428850B4C" srsName="EPSG:25832">
          <gml:pos>553167.784 5935003.046</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_CD54191D-1378-4B88-8607-210D44A08E4F">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553166.115 5934996.1757</gml:lowerCorner>
          <gml:upperCorner>553166.115 5934996.1757</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7d73c1ad-5988-426a-9615-686112ef6ad9" />
      <xplan:position>
        <gml:Point gml:id="Gml_CF1F9535-6C3D-4117-A8FC-3F35DFAF01F4" srsName="EPSG:25832">
          <gml:pos>553166.115 5934996.1757</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_1B982A82-6C0A-4E7E-88CD-B579DD4ECCF3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553164.9789 5934989.0023</gml:lowerCorner>
          <gml:upperCorner>553164.9789 5934989.0023</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GR</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7d73c1ad-5988-426a-9615-686112ef6ad9" />
      <xplan:position>
        <gml:Point gml:id="Gml_AEA2DED7-F011-4019-A626-7FDCB78BCF19" srsName="EPSG:25832">
          <gml:pos>553164.9789 5934989.0023</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_829bf8bd-d0e5-413d-b175-eccea06b696c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553184.306 5934926.641</gml:lowerCorner>
          <gml:upperCorner>553196.787 5934939.221</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_BF9C895B-0C82-4C81-BC6D-BB8F3D9D3C22" srsName="EPSG:25832">
          <gml:posList>553196.787 5934932.158 553190.72 5934926.641 553184.306 5934933.74 
553190.37 5934939.221 553196.787 5934932.158 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_2786bdeb-0989-4d34-b773-2bb9eec5a21c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552966.031 5934941.015</gml:lowerCorner>
          <gml:upperCorner>552975.051 5934961.949</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_BD105AF1-9CEA-472E-AC76-DD7F6ED8AE3D" srsName="EPSG:25832">
          <gml:posList>552966.031 5934941.015 552972.354 5934955.689 552974.704 5934961.145 
552975.051 5934961.949 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_da98b111-5052-442e-b1b1-131accdd40ec">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552886.39 5934957.536</gml:lowerCorner>
          <gml:upperCorner>552951.709 5935023.516</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0722af4b-5055-46f0-86ff-a1aa9a063932" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_19d26cd7-dc12-4e88-af48-a1e22579a229" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_85D31676-5405-4B55-B099-CEB5E6F3F83D" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_0A3D9FB3-1246-4863-9936-ED2A115B8D9D" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_5049CAD1-699B-4734-B835-C7959A95A20B" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552951.234 5934974.356 552951.709 5934976.337 552930.917 5934992.515 
552908.181 5935011.64 552894.064 5935023.516 552886.39 5935018.797 
552887.269 5935017.351 552907.864 5934989.746 552934.511 5934957.536 
552951.234 5934974.356 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GR uom="m2">130</xplan:GR>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0722af4b-5055-46f0-86ff-a1aa9a063932">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552940.736 5934978.139</gml:lowerCorner>
          <gml:upperCorner>552940.736 5934978.139</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_da98b111-5052-442e-b1b1-131accdd40ec" />
      <xplan:position>
        <gml:Point gml:id="Gml_D661B199-234E-4649-B9B9-4BD66B1F6D4A" srsName="EPSG:25832">
          <gml:pos>552940.736 5934978.139</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_19d26cd7-dc12-4e88-af48-a1e22579a229">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552934.05 5934978.163</gml:lowerCorner>
          <gml:upperCorner>552934.05 5934978.163</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_da98b111-5052-442e-b1b1-131accdd40ec" />
      <xplan:position>
        <gml:Point gml:id="Gml_9D73B6D7-481A-42AC-8891-35536AD583AD" srsName="EPSG:25832">
          <gml:pos>552934.05 5934978.163</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_85D31676-5405-4B55-B099-CEB5E6F3F83D">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552935.4745 5934972.2374</gml:lowerCorner>
          <gml:upperCorner>552935.4745 5934972.2374</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GR</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_da98b111-5052-442e-b1b1-131accdd40ec" />
      <xplan:position>
        <gml:Point gml:id="Gml_A003859D-1F5B-4CBD-A095-D8803ADC7DEE" srsName="EPSG:25832">
          <gml:pos>552935.4745 5934972.2374</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_0A3D9FB3-1246-4863-9936-ED2A115B8D9D">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552935.4745 5934966.099</gml:lowerCorner>
          <gml:upperCorner>552935.4745 5934966.099</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_da98b111-5052-442e-b1b1-131accdd40ec" />
      <xplan:position>
        <gml:Point gml:id="Gml_CF628B54-C843-4CEF-8870-48CB7687CBA9" srsName="EPSG:25832">
          <gml:pos>552935.4745 5934966.099</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_69a36291-20ba-4316-a7c5-37ef5323027e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553035.989 5934946.829</gml:lowerCorner>
          <gml:upperCorner>553106.838 5934992.677</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_96430730-a228-4abd-b09e-259849bae9e5" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_d0b354ca-832b-427b-9fd0-432e65db1b8a" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_56B95EB3-CEE2-4E38-9ED1-FBF3C2DA6427" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_7D36BF1C-C427-4601-8F8A-5810C7AB1DBE" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553103.453 5934949.588 553105.816 5934961.159 553106.838 5934966.16 
553090.67 5934967.534 553090.672 5934967.572 553091.292 5934982.29 
553091.729 5934992.677 553082.284 5934991.392 553069.378 5934981.975 
553050.42 5934968.139 553043.265 5934962.917 553035.989 5934957.607 
553082.68 5934948.635 553089.85 5934947.994 553102.888 5934946.829 
553103.453 5934949.588 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>6</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.5</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_96430730-a228-4abd-b09e-259849bae9e5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553070.142 5934976.915</gml:lowerCorner>
          <gml:upperCorner>553070.142 5934976.915</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_69a36291-20ba-4316-a7c5-37ef5323027e" />
      <xplan:position>
        <gml:Point gml:id="Gml_37330A39-9BE0-4D6E-BC38-3DC380C30D3A" srsName="EPSG:25832">
          <gml:pos>553070.142 5934976.915</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_d0b354ca-832b-427b-9fd0-432e65db1b8a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553060.423 5934968.865</gml:lowerCorner>
          <gml:upperCorner>553060.423 5934968.865</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_69a36291-20ba-4316-a7c5-37ef5323027e" />
      <xplan:position>
        <gml:Point gml:id="Gml_894A00E7-CE91-40F3-8F10-F204A6E5DE6E" srsName="EPSG:25832">
          <gml:pos>553060.423 5934968.865</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_56B95EB3-CEE2-4E38-9ED1-FBF3C2DA6427">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553065.0772 5934972.9651</gml:lowerCorner>
          <gml:upperCorner>553065.0772 5934972.9651</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_69a36291-20ba-4316-a7c5-37ef5323027e" />
      <xplan:position>
        <gml:Point gml:id="Gml_CAD34156-23DF-4CA4-9D9F-98F24D4F69C2" srsName="EPSG:25832">
          <gml:pos>553065.0772 5934972.9651</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_1850466b-b1dc-4744-b9f4-06426c8b3ace">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553161.461 5934901.86</gml:lowerCorner>
          <gml:upperCorner>553189.986 5934927.765</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_33E68494-8803-4C10-A03C-C7DEE2508B5B" srsName="EPSG:25832">
          <gml:posList>553165.324 5934919.012 553169.285 5934915.603 553173.178 5934912.251 
553185.339 5934901.86 553189.986 5934907.297 553177.838 5934917.678 
553173.99 5934920.98 553170.033 5934924.372 553166.17 5934927.765 
553161.461 5934922.405 553165.324 5934919.012 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_cc10aa85-21a6-46f4-872c-b2098fe715a3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553154.556 5934877.175</gml:lowerCorner>
          <gml:upperCorner>553165.791 5934890.868</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_686531c8-db4e-4cd1-809b-ae13980992a1" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_63F70D4A-B074-47D9-A069-92ED843CDF91" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553165.791 5934880.118 553160.506 5934890.868 553154.556 5934887.942 
553159.807 5934877.175 553165.791 5934880.118 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_686531c8-db4e-4cd1-809b-ae13980992a1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553160.169 5934884.024</gml:lowerCorner>
          <gml:upperCorner>553160.169 5934884.024</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_cc10aa85-21a6-46f4-872c-b2098fe715a3" />
      <xplan:position>
        <gml:Point gml:id="Gml_F3C87C18-A07A-4877-8BF0-5750EC0EA9D3" srsName="EPSG:25832">
          <gml:pos>553160.169 5934884.024</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_dcbca103-b877-45bd-837e-1fb75bb692b0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553112.018 5934886.122</gml:lowerCorner>
          <gml:upperCorner>553126.475 5934900.023</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_18eabc5a-0fdb-484d-a88f-b4ae016fde30" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_16779CE8-D245-45B9-8032-F7CEAA93B0F6" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553126.475 5934892.462 553124.377 5934895.368 553121.005 5934900.023 
553112.018 5934893.514 553117.358 5934886.122 553126.475 5934892.462 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_18eabc5a-0fdb-484d-a88f-b4ae016fde30">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553119.221 5934893.04</gml:lowerCorner>
          <gml:upperCorner>553119.221 5934893.04</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_dcbca103-b877-45bd-837e-1fb75bb692b0" />
      <xplan:position>
        <gml:Point gml:id="Gml_B12BBD7C-799C-483D-9BEF-D39A85FF04E4" srsName="EPSG:25832">
          <gml:pos>553119.221 5934893.04</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_45fefdb5-370b-4239-a343-b3a0172c32c0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553093.69 5934857.416</gml:lowerCorner>
          <gml:upperCorner>553112.376 5934871.302</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_6ce2f8c1-77ef-46a0-97ec-1e22c08e6ebe" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_1C25F279-EA2B-4E57-B6CE-D64B48900A82" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553104.608 5934871.302 553101.008 5934868.891 553093.69 5934863.988 
553095.905 5934861.528 553099.041 5934863.912 553101.537 5934860.203 
553102.187 5934860.641 553104.358 5934857.416 553112.376 5934862.819 
553110.092 5934866.212 553110.878 5934866.742 553108.3 5934870.577 
553106.09 5934869.092 553104.608 5934871.302 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_6ce2f8c1-77ef-46a0-97ec-1e22c08e6ebe">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553105.932 5934863.734</gml:lowerCorner>
          <gml:upperCorner>553105.932 5934863.734</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_45fefdb5-370b-4239-a343-b3a0172c32c0" />
      <xplan:position>
        <gml:Point gml:id="Gml_C1FE9523-7022-49FF-A2E4-2768B3E3FBBF" srsName="EPSG:25832">
          <gml:pos>553105.932 5934863.734</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_4ee3d8ee-0238-4bbf-850b-4947561221fa">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553090.67 5934966.16</gml:lowerCorner>
          <gml:upperCorner>553106.838 5934992.677</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_6A1BF760-435F-4366-967E-44F51447F298" srsName="EPSG:25832">
          <gml:posList>553106.838 5934966.16 553090.67 5934967.534 553090.672 5934967.572 
553091.292 5934982.29 553091.729 5934992.677 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_a7d50438-df48-431f-86b5-cb7013f12614">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553131.071 5935000.04</gml:lowerCorner>
          <gml:upperCorner>553140.097 5935025.234</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_1E0027DA-FE6A-408D-A861-76604EBE4F32" srsName="EPSG:25832">
          <gml:posList>553137.515 5935025.234 553140.097 5935016.377 553131.071 5935000.04 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_e753c661-3c9c-4628-8ee3-77ab9f55e843">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553012.997 5934901.179</gml:lowerCorner>
          <gml:upperCorner>553029.294 5934915.092</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_A9719CB1-CB21-4EF9-A3AD-18499295900F" srsName="EPSG:25832">
          <gml:posList>553029.294 5934908.199 553026.488 5934911.029 553026.911 5934911.448 
553023.296 5934915.092 553019.399 5934911.225 553017.496 5934913.142 
553012.997 5934908.676 553018.247 5934903.39 553019.138 5934904.274 
553022.207 5934901.179 553029.294 5934908.199 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_1ceb3413-e1db-4284-953c-4ca527fcf01c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553171.528 5934932.327</gml:lowerCorner>
          <gml:upperCorner>553198.992 5934954.625</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_2927f2d3-73dd-4331-9a35-d39dd2bcab39" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_87A85004-6371-425C-A304-A4A893AC0F40" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_F76401B7-60CD-4A0B-81B2-15F9A2AECB0C" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_16C8B932-543C-451B-99C0-D5C2E8359315" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553190.073 5934940.995 553198.992 5934947.919 553194.696 5934954.625 
553189.882 5934951.189 553187.507 5934949.495 553185.432 5934948.014 
553184.403 5934947.356 553177.7 5934943.066 553177.07 5934942.663 
553171.528 5934939.116 553175.469 5934933.103 553176.754 5934932.327 
553190.073 5934940.995 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>2000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_2927f2d3-73dd-4331-9a35-d39dd2bcab39">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553181.944 5934942.679</gml:lowerCorner>
          <gml:upperCorner>553181.944 5934942.679</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1ceb3413-e1db-4284-953c-4ca527fcf01c" />
      <xplan:position>
        <gml:Point gml:id="Gml_66D14865-D3C3-408B-8DA7-A53DB6EB42C4" srsName="EPSG:25832">
          <gml:pos>553181.944 5934942.679</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_87A85004-6371-425C-A304-A4A893AC0F40">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553176.6983 5934937.3896</gml:lowerCorner>
          <gml:upperCorner>553176.6983 5934937.3896</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1ceb3413-e1db-4284-953c-4ca527fcf01c" />
      <xplan:position>
        <gml:Point gml:id="Gml_6D910387-AE7B-4183-83A7-2A89916A8CDB" srsName="EPSG:25832">
          <gml:pos>553176.6983 5934937.3896</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_F76401B7-60CD-4A0B-81B2-15F9A2AECB0C">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553194.7959 5934950.0896</gml:lowerCorner>
          <gml:upperCorner>553194.7959 5934950.0896</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1ceb3413-e1db-4284-953c-4ca527fcf01c" />
      <xplan:position>
        <gml:Point gml:id="Gml_DAD5C9EA-6CEB-49B8-AB04-41724A48E061" srsName="EPSG:25832">
          <gml:pos>553194.7959 5934950.0896</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_9f045ee5-643a-44b2-b9b8-2bf190ff3189">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553186.952 5934942.096</gml:lowerCorner>
          <gml:upperCorner>553189.316 5934945.761</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_08581371-EAAB-4E6C-9D17-07323B18C427" srsName="EPSG:25832">
          <gml:posList>553186.952 5934945.761 553189.316 5934942.096 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_b6bda54f-4ad9-4927-8274-46730769d56f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553107.236 5934963.792</gml:lowerCorner>
          <gml:upperCorner>553142.02 5934983.676</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_3517CA60-DB29-46C9-8BC6-A2AEFE3219CD" srsName="EPSG:25832">
          <gml:posList>553107.236 5934968.106 553133.621 5934963.792 553137.363 5934972.324 
553137.559 5934972.772 553138.333 5934974.536 553139.802 5934977.884 
553142.02 5934983.676 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_0dbe3df4-4c94-4324-aa25-79c3f71825c6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553144.048 5934982.848</gml:lowerCorner>
          <gml:upperCorner>553156.382 5935010.751</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_1a22d80d-006f-4e41-b301-68c295a4ee61" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_789D7ABD-A69F-470F-B4CF-D44CE23C92ED" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553153.649 5934983.666 553154.809 5935001.169 553156.146 5935007.135 
553156.382 5935010.117 553153.206 5935010.751 553146.835 5934992.989 
553144.048 5934990.199 553149.427 5934982.848 553153.649 5934983.666 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_1a22d80d-006f-4e41-b301-68c295a4ee61">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553150.624 5934991.853</gml:lowerCorner>
          <gml:upperCorner>553150.624 5934991.853</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0dbe3df4-4c94-4324-aa25-79c3f71825c6" />
      <xplan:position>
        <gml:Point gml:id="Gml_30CD6E10-DFD0-4AFB-87AB-AB0470020352" srsName="EPSG:25832">
          <gml:pos>553150.624 5934991.853</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_816da885-bbc3-4320-90e1-2cfb96c5513d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553106.706 5934948.585</gml:lowerCorner>
          <gml:upperCorner>553118.388 5934961.807</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_4851A53E-37DF-4DD4-912B-1E42628E310B" srsName="EPSG:25832">
          <gml:posList>553115.028 5934948.585 553118.388 5934956.107 553113.864 5934956.653 
553114.31 5934961.308 553108.187 5934961.807 553106.706 5934949.59 
553115.028 5934948.585 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_c76af1c9-22c1-4d7b-a025-9598590f1497">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553165.374 5934933.212</gml:lowerCorner>
          <gml:upperCorner>553299.972 5935070.404</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_729cf43f-e356-4e0f-9cf7-ef88fb8bece1" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_59C98B99-9EC5-4AAD-8113-1D3AB96C25BE" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_0C250944-2209-47D0-95A0-52FB69F55371" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_B23B77AA-A36F-43FD-9F9D-D487F9FF6B37" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553253.356 5934985.837 553261.183 5934997.286 553264.006 5935001.415 
553274.278 5935016.443 553299.972 5935051.125 553294.9752 5935054.3542 
553258.2483 5935066.594 553242.1183 5935067.0238 553230.74 5935070.32 
553225.953 5935070.404 553209.094 5935069.728 553190.206 5935069.693 
553184.408 5935058.141 553176.261 5935047.118 553168.862 5935026.498 
553165.374 5935017.085 553173.912 5935015.05 553169.567 5934985.588 
553169.074 5934978.061 553168.921 5934975.398 553180.889 5934977.757 
553184.472 5934969.866 553189.433 5934961.594 553194.696 5934954.625 
553198.992 5934947.919 553210.329 5934936.179 553215.029 5934933.212 
553253.356 5934985.837 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:GR uom="m2">300</xplan:GR>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_729cf43f-e356-4e0f-9cf7-ef88fb8bece1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553204.502 5934985.465</gml:lowerCorner>
          <gml:upperCorner>553204.502 5934985.465</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c76af1c9-22c1-4d7b-a025-9598590f1497" />
      <xplan:position>
        <gml:Point gml:id="Gml_2346520A-D433-4260-87C7-251D3526AD8B" srsName="EPSG:25832">
          <gml:pos>553204.502 5934985.465</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_59C98B99-9EC5-4AAD-8113-1D3AB96C25BE">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553215.9793 5934985.4896</gml:lowerCorner>
          <gml:upperCorner>553215.9793 5934985.4896</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c76af1c9-22c1-4d7b-a025-9598590f1497" />
      <xplan:position>
        <gml:Point gml:id="Gml_34176A11-AA50-49D0-89E2-694AD9D54AEB" srsName="EPSG:25832">
          <gml:pos>553215.9793 5934985.4896</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_0C250944-2209-47D0-95A0-52FB69F55371">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553209.6293 5934976.4938</gml:lowerCorner>
          <gml:upperCorner>553209.6293 5934976.4938</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GR</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c76af1c9-22c1-4d7b-a025-9598590f1497" />
      <xplan:position>
        <gml:Point gml:id="Gml_9D9D567E-75F2-4FC7-842B-31029FDEC7B7" srsName="EPSG:25832">
          <gml:pos>553209.6293 5934976.4938</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_AnpflanzungBindungErhaltung gml:id="GML_e33890b6-857b-47ca-9ab7-e66a86e56b60">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553261.183 5934970.42</gml:lowerCorner>
          <gml:upperCorner>553306.128 5935001.415</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_556AA119-06EB-4E39-8BFF-22C4E92AEAD6" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553306.128 5934974.321 553292.38 5934983.173 553264.006 5935001.415 
553261.183 5934997.286 553289.676 5934978.967 553302.95 5934970.42 
553306.128 5934974.321 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:massnahme>2000</xplan:massnahme>
      <xplan:gegenstand>2000</xplan:gegenstand>
    </xplan:BP_AnpflanzungBindungErhaltung>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_6b56aca0-b58b-4c68-aeac-46c13ea81ce7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553086.305 5934916.566</gml:lowerCorner>
          <gml:upperCorner>553100.376 5934937.073</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ee977ef8-de15-4c7b-8ead-39fc4d04370b" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_2EF7DAAF-5CD2-4939-B4CF-C880E078FE3A" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553095.717 5934916.619 553095.549 5934924.263 553095.474 5934927.624 
553100.376 5934927.739 553100.164 5934937.073 553086.305 5934936.735 
553086.689 5934923.789 553086.698 5934922.176 553086.73 5934916.566 
553095.717 5934916.619 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ee977ef8-de15-4c7b-8ead-39fc4d04370b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553091.42 5934925.557</gml:lowerCorner>
          <gml:upperCorner>553091.42 5934925.557</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_6b56aca0-b58b-4c68-aeac-46c13ea81ce7" />
      <xplan:position>
        <gml:Point gml:id="Gml_955D2E5B-C70C-42B9-8AC6-99B4D90F1A90" srsName="EPSG:25832">
          <gml:pos>553091.42 5934925.557</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_a9f7ac0a-9d98-4f82-81ad-6ebeb13ae18f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553078.363 5935027.505</gml:lowerCorner>
          <gml:upperCorner>553102.218 5935061.752</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:text>Sonstige Abgrenzung</xplan:text>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_38A3B0FD-9414-487C-9772-AF6A3566AB66" srsName="EPSG:25832">
          <gml:posList>553078.363 5935061.752 553082.877 5935055.27 553086.17 5935050.542 
553102.218 5935027.505 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>9999</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_98c27262-2fb4-49f7-84d2-eec597062863">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.888 5934941.6</gml:lowerCorner>
          <gml:upperCorner>553158.31 5934983.676</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_60179d2f-11cd-4afc-ab84-1ae7658b1aa6" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_fa813021-0be7-41dc-8363-da5e5de709ce" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_F057FEF3-780F-40A3-B093-1E82D852137B" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_947876A1-2C6C-43F9-B00B-E24CD83259B1" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_FCD843AD-3BE1-4544-B124-2ECBDE63093A" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553150.281 5934970.732 553149.925 5934971.29 553142.02 5934983.676 
553139.802 5934977.884 553138.333 5934974.536 553137.559 5934972.772 
553137.363 5934972.324 553133.621 5934963.792 553107.236 5934968.106 
553106.838 5934966.16 553105.816 5934961.159 553103.453 5934949.588 
553102.888 5934946.829 553113.808 5934945.854 553125.302 5934944.827 
553135.533 5934943.913 553157.345 5934941.6 553158.31 5934945.4 
553156.585 5934953.467 553156.303 5934954.786 553153.509 5934962.184 
553150.281 5934970.732 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_60179d2f-11cd-4afc-ab84-1ae7658b1aa6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553133.2294 5934947.548</gml:lowerCorner>
          <gml:upperCorner>553133.2294 5934947.548</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98c27262-2fb4-49f7-84d2-eec597062863" />
      <xplan:position>
        <gml:Point gml:id="Gml_8FE368B0-8ADE-423D-90A6-A81AACB17799" srsName="EPSG:25832">
          <gml:pos>553133.2294 5934947.548</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_fa813021-0be7-41dc-8363-da5e5de709ce">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553136.239 5934957.915</gml:lowerCorner>
          <gml:upperCorner>553136.239 5934957.915</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98c27262-2fb4-49f7-84d2-eec597062863" />
      <xplan:position>
        <gml:Point gml:id="Gml_13C8EDE2-6BE5-4E25-9858-1398C2EDE1E2" srsName="EPSG:25832">
          <gml:pos>553136.239 5934957.915</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_F057FEF3-780F-40A3-B093-1E82D852137B">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553135.0881 5934952.784</gml:lowerCorner>
          <gml:upperCorner>553135.0881 5934952.784</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98c27262-2fb4-49f7-84d2-eec597062863" />
      <xplan:position>
        <gml:Point gml:id="Gml_6F047412-F42F-4802-9BF5-811C0592912D" srsName="EPSG:25832">
          <gml:pos>553135.0881 5934952.784</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_947876A1-2C6C-43F9-B00B-E24CD83259B1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553118.1713 5934961.6806</gml:lowerCorner>
          <gml:upperCorner>553118.1713 5934961.6806</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98c27262-2fb4-49f7-84d2-eec597062863" />
      <xplan:position>
        <gml:Point gml:id="Gml_D0A7916D-9C3B-45BF-8D5A-C42D0A71E4CE" srsName="EPSG:25832">
          <gml:pos>553118.1713 5934961.6806</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_9a4c52e1-2af1-4988-aeb0-d16381fedd5a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553215.029 5934933.212</gml:lowerCorner>
          <gml:upperCorner>553289.676 5934997.286</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_C3716304-50D1-4C74-8AF4-7C63F306F0FC" srsName="EPSG:25832">
          <gml:posList>553277.975 5934949.65 553271.471 5934954.505 553289.676 5934978.967 
553261.183 5934997.286 553253.356 5934985.837 553215.029 5934933.212 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_e49decfd-bd31-439d-9241-41e7a5836627">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553168.921 5934917.011</gml:lowerCorner>
          <gml:upperCorner>553240.721 5934977.757</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_A0403013-0BA5-479E-BA0C-0B0A6B77428C" srsName="EPSG:25832">
          <gml:posList>553168.921 5934975.398 553180.889 5934977.757 553184.472 5934969.866 
553189.433 5934961.594 553194.696 5934954.625 553198.992 5934947.919 
553210.329 5934936.179 553215.029 5934933.212 553227.184 5934925.38 
553228.929 5934924.441 553229.109 5934924.689 553240.721 5934917.011 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_22fb4f62-11a2-4ee9-9823-e99f0582359a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553113.403 5934980.741</gml:lowerCorner>
          <gml:upperCorner>553126.681 5934996.76</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_15ea1be7-2ae9-4ce3-85c2-34d5c55f67d9" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_322BF319-D1FD-40BC-84A0-33D7A14598D7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553126.681 5934993.129 553118.301 5934996.76 553113.403 5934984.136 
553121.316 5934980.741 553126.681 5934993.129 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_15ea1be7-2ae9-4ce3-85c2-34d5c55f67d9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553120.777 5934990.761</gml:lowerCorner>
          <gml:upperCorner>553120.777 5934990.761</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_22fb4f62-11a2-4ee9-9823-e99f0582359a" />
      <xplan:position>
        <gml:Point gml:id="Gml_4E5A76E9-0FA4-4BF5-96DE-403BA07278A5" srsName="EPSG:25832">
          <gml:pos>553120.777 5934990.761</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_7b362be2-9b58-4fb0-a304-a3c2ba0493b0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553117.29 5934947.604</gml:lowerCorner>
          <gml:upperCorner>553130.868 5934960.826</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_9a6e30e0-5534-44c1-947a-ea7930f6f89b" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0CE96E07-C2CB-48AF-96A0-C202411BBDC0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553130.058 5934955.668 553130.868 5934957.515 553122.721 5934960.826 
553117.29 5934948.542 553126.521 5934947.604 553130.058 5934955.668 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_9a6e30e0-5534-44c1-947a-ea7930f6f89b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553123.875 5934952.541</gml:lowerCorner>
          <gml:upperCorner>553123.875 5934952.541</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7b362be2-9b58-4fb0-a304-a3c2ba0493b0" />
      <xplan:position>
        <gml:Point gml:id="Gml_B8E2AB29-87F5-469B-BDF8-EF3A9FD830F7" srsName="EPSG:25832">
          <gml:pos>553123.875 5934952.541</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_fd657926-28e8-49b5-9b5b-fe6ef646b6df">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553136.869 5934959.209</gml:lowerCorner>
          <gml:upperCorner>553152.776 5934974.657</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_839d672d-dcdd-4a08-84c9-c4859be6b394" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_80E01F0B-725D-41FB-9381-E8A0E4C96F2E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553152.776 5934964.124 553150.281 5934970.732 553149.925 5934971.29 
553142.813 5934974.657 553136.869 5934962.484 553141.284 5934960.211 
553142.288 5934962.25 553149.78 5934959.209 553152.776 5934964.124 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_839d672d-dcdd-4a08-84c9-c4859be6b394">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553145.592 5934966.995</gml:lowerCorner>
          <gml:upperCorner>553145.592 5934966.995</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_fd657926-28e8-49b5-9b5b-fe6ef646b6df" />
      <xplan:position>
        <gml:Point gml:id="Gml_6EBEFCB7-2F60-4C38-B516-97422D8A4CDF" srsName="EPSG:25832">
          <gml:pos>553145.592 5934966.995</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_4ff56b4b-da5e-4cba-8139-9dd736ba8e08">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553103.725 5935053.885</gml:lowerCorner>
          <gml:upperCorner>553115.304 5935065.97</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_cc8649ef-5a5e-449c-b630-8a3265c27fef" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_839F5C83-D22E-43D5-B553-6E8B6A2D9649" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553115.304 5935054.657 553114.446 5935065.97 553103.725 5935065.166 
553104.452 5935053.885 553115.304 5935054.657 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_cc8649ef-5a5e-449c-b630-8a3265c27fef">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553108.8575 5935063.0113</gml:lowerCorner>
          <gml:upperCorner>553108.8575 5935063.0113</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4ff56b4b-da5e-4cba-8139-9dd736ba8e08" />
      <xplan:position>
        <gml:Point gml:id="Gml_B733BBF2-96CD-4BA9-BECF-A6517E352272" srsName="EPSG:25832">
          <gml:pos>553108.8575 5935063.0113</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_0ce38652-a0fa-4c31-8200-5401767c60e3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553151.158 5935029.425</gml:lowerCorner>
          <gml:upperCorner>553167.249 5935029.663</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_4DA5E8BD-99AC-45EE-A3F8-8DACCB130D09" srsName="EPSG:25832">
          <gml:posList>553151.158 5935029.663 553167.249 5935029.425 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_10510a15-3fe1-4b05-a58f-462703e8bdbc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553106.706 5934948.585</gml:lowerCorner>
          <gml:upperCorner>553118.388 5934961.807</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_140093af-4430-4f5c-abae-f299cba3b63b" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0A7FB23A-5D1B-4704-8CDA-E1F274278468" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553118.388 5934956.107 553113.864 5934956.653 553114.31 5934961.308 
553108.187 5934961.807 553106.706 5934949.59 553115.028 5934948.585 
553118.388 5934956.107 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_140093af-4430-4f5c-abae-f299cba3b63b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553111.853 5934952.932</gml:lowerCorner>
          <gml:upperCorner>553111.853 5934952.932</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_10510a15-3fe1-4b05-a58f-462703e8bdbc" />
      <xplan:position>
        <gml:Point gml:id="Gml_8D0114AE-0223-4680-AE3C-D331C41FFAB2" srsName="EPSG:25832">
          <gml:pos>553111.853 5934952.932</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_5a5e05c0-3a0d-439e-bdde-e8bfadaa9903">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553153.149 5934862.162</gml:lowerCorner>
          <gml:upperCorner>553161.928 5934871.055</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_58f3600e-e8b8-4f07-8806-d4378df0f9bf" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_EA92AE1D-837E-4D09-92CD-07C37189FCF8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553161.928 5934865.596 553158.266 5934871.055 553153.149 5934867.58 
553156.849 5934862.162 553161.928 5934865.596 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_58f3600e-e8b8-4f07-8806-d4378df0f9bf">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553157.546 5934866.6</gml:lowerCorner>
          <gml:upperCorner>553157.546 5934866.6</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5a5e05c0-3a0d-439e-bdde-e8bfadaa9903" />
      <xplan:position>
        <gml:Point gml:id="Gml_D20B4C3B-C00D-4899-83F9-78F0A61A984A" srsName="EPSG:25832">
          <gml:pos>553157.546 5934866.6</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_602ed388-6ddf-4dc9-b720-bf2df0264d73">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552968.201 5934934.949</gml:lowerCorner>
          <gml:upperCorner>552989.421 5934953.257</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_A5CB9D58-9647-4254-8883-EB4223F28D59" srsName="EPSG:25832">
          <gml:posList>552989.421 5934946.569 552979.795 5934951.712 552979.087 5934950.387 
552973.711 5934953.257 552968.201 5934942.944 552973.484 5934940.131 
552983.219 5934934.949 552985.506 5934939.234 552989.421 5934946.569 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_a242d7ba-42a0-4c10-9fe0-b49558383350">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553215.039 5934893.432</gml:lowerCorner>
          <gml:upperCorner>553227.73 5934905.295</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_F6DFF3B0-28B3-4C8B-BF77-16A7A3200572" srsName="EPSG:25832">
          <gml:posList>553218.521 5934893.432 553227.73 5934900.365 553224.02 5934905.295 
553215.039 5934898.534 553218.521 5934893.432 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_537d67d0-28c0-4fe0-b915-e6bfd54d09e0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552918.107 5934978.538</gml:lowerCorner>
          <gml:upperCorner>552934.286 5934994.296</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_E21C9D6E-EA56-45DC-9074-01A0A4817FC0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552934.286 5934986.632 552924.796 5934994.296 552918.107 5934986.014 
552927.372 5934978.538 552934.286 5934986.632 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553078.363 5935027.505</gml:lowerCorner>
          <gml:upperCorner>553110.462 5935069.942</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_a60af9b2-0fae-4b06-b82f-8c0a89384d1c" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_844E9879-692A-4220-BC9B-6A787829C224" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_15ED2463-1D1F-4895-9B16-AD856959D2E1" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_16EA18D6-7667-410E-9552-6A02951F0497" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_BAC2F703-D7F0-4743-A5D2-AEB0B1DACC0F" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_1699991F-6764-4CEE-9E04-01C821EB6AB5" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553103.983 5935031.706 553110.462 5935038.171 553095.689 5935069.942 
553092.331 5935069.18 553086.919 5935066.977 553084.065 5935065.746 
553083.305 5935065.342 553082.896 5935064.915 553078.363 5935061.752 
553082.877 5935055.27 553086.17 5935050.542 553102.218 5935027.505 
553103.983 5935031.706 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>4</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_a60af9b2-0fae-4b06-b82f-8c0a89384d1c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553099.0245 5935006.115</gml:lowerCorner>
          <gml:upperCorner>553099.0245 5935006.115</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34" />
      <xplan:position>
        <gml:Point gml:id="Gml_17FF04E3-22E7-4D5D-8E3E-55BBE67F6546" srsName="EPSG:25832">
          <gml:pos>553099.0245 5935006.115</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_844E9879-692A-4220-BC9B-6A787829C224">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553088.5458 5935062.8972</gml:lowerCorner>
          <gml:upperCorner>553088.5458 5935062.8972</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34" />
      <xplan:position>
        <gml:Point gml:id="Gml_356DBF86-9723-4A60-9403-3C7EF2A1B488" srsName="EPSG:25832">
          <gml:pos>553088.5458 5935062.8972</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_15ED2463-1D1F-4895-9B16-AD856959D2E1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553095.0943 5935051.2092</gml:lowerCorner>
          <gml:upperCorner>553095.0943 5935051.2092</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34" />
      <xplan:position>
        <gml:Point gml:id="Gml_87991E92-9AB5-44BD-A7F7-B00D145127EF" srsName="EPSG:25832">
          <gml:pos>553095.0943 5935051.2092</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_16EA18D6-7667-410E-9552-6A02951F0497">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553098.2957 5935044.7203</gml:lowerCorner>
          <gml:upperCorner>553098.2957 5935044.7203</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34" />
      <xplan:position>
        <gml:Point gml:id="Gml_C76A7FFE-FE33-4707-9281-0D6FC03D00B8" srsName="EPSG:25832">
          <gml:pos>553098.2957 5935044.7203</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_BAC2F703-D7F0-4743-A5D2-AEB0B1DACC0F">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553101.0739 5935038.5687</gml:lowerCorner>
          <gml:upperCorner>553101.0739 5935038.5687</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_cd8f7b55-8b45-4d93-acf6-ae52fb1d9c34" />
      <xplan:position>
        <gml:Point gml:id="Gml_6F81C497-EE72-4940-A536-F89C14EE5DD8" srsName="EPSG:25832">
          <gml:pos>553101.0739 5935038.5687</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_8898d662-079a-466c-a813-eb8d3f92ff39">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553135.853 5934998.472</gml:lowerCorner>
          <gml:upperCorner>553151.075 5935011.831</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_2b92f5ad-86dc-469b-9cc5-2a8f63ad0f14" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_40CA6435-AC22-4A17-9065-6B7A5F185EE9" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553146.108 5935003.993 553151.075 5935011.177 553147.804 5935011.831 
553135.853 5935001.3 553138.214 5934998.472 553146.108 5935003.993 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_2b92f5ad-86dc-469b-9cc5-2a8f63ad0f14">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.01 5935005.706</gml:lowerCorner>
          <gml:upperCorner>553143.01 5935005.706</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8898d662-079a-466c-a813-eb8d3f92ff39" />
      <xplan:position>
        <gml:Point gml:id="Gml_061E4783-2EB5-4C1C-9433-9C535AB92F67" srsName="EPSG:25832">
          <gml:pos>553143.01 5935005.706</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_eb31558b-f294-44a4-bae2-ca3025816d1e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553131.82 5934893.738</gml:lowerCorner>
          <gml:upperCorner>553147.821 5934908.683</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_bc891081-5c85-4443-9c69-f5a26ad281a7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_5C01580E-C000-4A6A-B8DA-6413EC900925" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553147.821 5934901.269 553142.676 5934908.683 553131.82 5934901.126 
553136.969 5934893.738 553147.821 5934901.269 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_bc891081-5c85-4443-9c69-f5a26ad281a7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553138.046 5934899.759</gml:lowerCorner>
          <gml:upperCorner>553138.046 5934899.759</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_eb31558b-f294-44a4-bae2-ca3025816d1e" />
      <xplan:position>
        <gml:Point gml:id="Gml_07D5CF85-A3D3-4FEE-978D-001CF869BBCC" srsName="EPSG:25832">
          <gml:pos>553138.046 5934899.759</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_618641e4-7c14-4a65-bf1e-6915c38b7d72">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553089.977 5934949.588</gml:lowerCorner>
          <gml:upperCorner>553105.816 5934962.49</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_29d54638-4843-4e20-b72c-40d123e6ffa8" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_16BF16E1-B832-4798-84C5-DA0A27F4FE80" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553105.816 5934961.159 553090.458 5934962.49 553089.977 5934951.036 
553103.453 5934949.588 553105.816 5934961.159 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_29d54638-4843-4e20-b72c-40d123e6ffa8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553097.427 5934956.053</gml:lowerCorner>
          <gml:upperCorner>553097.427 5934956.053</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_618641e4-7c14-4a65-bf1e-6915c38b7d72" />
      <xplan:position>
        <gml:Point gml:id="Gml_F16FE612-4998-4165-ACAD-48C1F4D1AF58" srsName="EPSG:25832">
          <gml:pos>553097.427 5934956.053</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_GemeinbedarfsFlaeche gml:id="GML_e30e1b9b-89de-4c1c-becb-55482a747f45">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553261.183 5934970.42</gml:lowerCorner>
          <gml:upperCorner>553340.636 5935051.125</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_5be51555-9d12-441c-b545-5610a3d4c443" />    
      <xplan:wirdDargestelltDurch xlink:href="#Gml_1EC65BFB-F3E6-487F-A8C4-E80F7FB8A6B4" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_6BA7AA07-8184-48A9-9E8B-D52A88182FBF" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553320.571 5934992.049 553335.751 5935010.241 553339.4069 5935014.3468 
553340.636 5935015.727 553326.028 5935033.877 553320.527 5935037.519 
553299.972 5935051.125 553274.278 5935016.443 553264.006 5935001.415 
553261.183 5934997.286 553289.676 5934978.967 553302.95 5934970.42 
553320.571 5934992.049 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:zweckbestimmung>
        <xplan:BP_KomplexeZweckbestGemeinbedarf>
          <xplan:allgemein>12000</xplan:allgemein>          
        </xplan:BP_KomplexeZweckbestGemeinbedarf>
      </xplan:zweckbestimmung>
      <xplan:zweckbestimmung>
        <xplan:BP_KomplexeZweckbestGemeinbedarf>
          <xplan:allgemein>1200</xplan:allgemein>          
        </xplan:BP_KomplexeZweckbestGemeinbedarf>
      </xplan:zweckbestimmung>      
      <xplan:zugunstenVon>Freie und Hansestadt Hamburg</xplan:zugunstenVon>
    </xplan:BP_GemeinbedarfsFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_5be51555-9d12-441c-b545-5610a3d4c443">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553302.511 5935003.989</gml:lowerCorner>
          <gml:upperCorner>553302.511 5935003.989</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_11.01</xplan:stylesheetId>
      <xplan:art>zugunstenVon</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e30e1b9b-89de-4c1c-becb-55482a747f45" />
      <xplan:position>
        <gml:Point gml:id="Gml_4DA7A5C9-8B19-40B0-86D3-9465C6B3530B" srsName="EPSG:25832">
          <gml:pos>553302.511 5935003.989</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>  
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_1EC65BFB-F3E6-487F-A8C4-E80F7FB8A6B4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553302.6503 5935011.8369</gml:lowerCorner>
          <gml:upperCorner>553302.6503 5935011.8369</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e30e1b9b-89de-4c1c-becb-55482a747f45" />
      <xplan:position>
        <gml:Point gml:id="Gml_CA74D36D-B1BC-4EA0-9020-937CE80ABD70" srsName="EPSG:25832">
          <gml:pos>553302.6503 5935011.8369</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_d4ad2294-92ad-424c-a65b-3648a83fa251">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553091.801 5934890.277</gml:lowerCorner>
          <gml:upperCorner>553122.427 5934907.291</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_0EE31E5C-0357-404D-B894-90508C398DA5" srsName="EPSG:25832">
          <gml:posList>553091.801 5934907.291 553104.56 5934890.277 553108.47 5934893.119 
553122.427 5934903.263 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_99f42f84-23d5-4d4a-8a5d-985f5da248b1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553157.026 5934827.453</gml:lowerCorner>
          <gml:upperCorner>553167.304 5934840.559</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_41505B51-3A14-47A3-9C5C-55A964E7D931" srsName="EPSG:25832">
          <gml:posList>553157.026 5934829.268 553164.617 5934827.453 553167.304 5934838.71 
553159.552 5934840.559 553157.026 5934829.268 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_efabfe6b-27c1-47c7-b1db-238bf2c2ef24">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553179.999 5934844.368</gml:lowerCorner>
          <gml:upperCorner>553197.594 5934860.432</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_F9C84CC0-28B7-47B4-A4CE-E98C781FB219" srsName="EPSG:25832">
          <gml:posList>553197.594 5934852.408 553192.072 5934860.432 553184.164 5934854.979 
553185.733 5934852.703 553179.999 5934848.75 553185.004 5934845.532 
553190.446 5934844.368 553193.55 5934846.503 553192.092 5934848.623 
553197.594 5934852.408 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_eb2c55e1-95ab-4047-b5ed-cef618975e6c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553032.5 5934981.975</gml:lowerCorner>
          <gml:upperCorner>553069.378 5935013.818</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:text>Sonstige Abgrenzung</xplan:text>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_5AAEBAF8-358A-41A6-A1F0-6C51F8CCA3BE" srsName="EPSG:25832">
          <gml:posList>553069.378 5934981.975 553032.5 5935013.818 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>9999</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:SO_Gebiet gml:id="GML_8d116d2f-509e-4597-9c8f-5bb5527abe66">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552878.613 5934783.032</gml:lowerCorner>
          <gml:upperCorner>553302.95 5935078.629</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:refTextInhalt xlink:href="#GML_3a49acbe-ac34-43f4-aaff-4714285df683" />
      <xplan:refTextInhalt xlink:href="#GML_8576d16d-cd52-47d5-bff1-69d7ed57a5c9" />
      <xplan:refTextInhalt xlink:href="#GML_7b5fc84e-19e1-4859-877b-c21380a35199" />
      <xplan:refTextInhalt xlink:href="#GML_c0b8e69e-e02b-4667-8d04-dc323bab8ea7" />
      <xplan:refTextInhalt xlink:href="#GML_43a887c4-c8fc-4a7c-91eb-2e800b2e59f7" />
      <xplan:refTextInhalt xlink:href="#GML_b4be074c-b09a-4d1c-8bb0-46097cf8e410" />
      <xplan:refTextInhalt xlink:href="#GML_6818d377-335e-4ce3-8e08-d1195d504ad7" />
      <xplan:refTextInhalt xlink:href="#GML_b53df60c-050a-4134-af60-c8bc6176f136" />
      <xplan:refTextInhalt xlink:href="#GML_6d3d0605-1142-42b3-a412-6d7004f8ede0" />
      <xplan:position>
        <gml:Polygon gml:id="Gml_97ED1D78-CB5D-4D93-B505-D97A406469C9" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_47341D6B-930E-4B57-B4E1-6E7F97DC75D9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553184.083 5935078.629 553142.762 5935074.21 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4766E638-31C4-46CE-9694-1A2181818055" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553142.762 5935074.21 553123.501 5935072.151 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_87CCA838-514B-40EE-8F9A-09F37B2D63CB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553123.501 5935072.151 553115.737 5935069.696 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_C6C21524-552C-4CBE-8CAC-E4E67DD88344" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553115.737 5935069.696 553110.286565974 5935068.68207358 553105.475 5935071.436 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2E70E7B0-4A82-467C-AF09-CE934AB52786" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553105.475 5935071.436 553103.762 5935071.226 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F6ABE487-54F8-4C12-B6FE-6BFFA4367EF3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553103.762 5935071.226 553097.811 5935070.423 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6F21DD6B-6056-4648-BF10-319EE9F53C6C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553097.811 5935070.423 553095.689 5935069.942 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BB2CAFEA-B4D1-49C8-B6D5-E425B776C2ED" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553095.689 5935069.942 553092.331 5935069.18 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AF351040-0908-435D-8A54-97EAB54C8C3F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553092.331 5935069.18 553086.919 5935066.977 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C3937C46-1303-4479-82B2-DBCEAA074A71" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553086.919 5935066.977 553084.065 5935065.746 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9DED8DBA-8343-4AC7-8ED5-907FA9B93EA4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553084.065 5935065.746 553083.305 5935065.342 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8B1EBBF0-84B1-4860-ACE1-653164899151" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553083.305 5935065.342 553082.896 5935064.915 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7411DD7B-80E8-4FDE-8EE4-5A8C4F70ACA9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553082.896 5935064.915 553078.363 5935061.752 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_76C6CF2C-70E7-4EAE-89E0-9495D9815288" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553078.363 5935061.752 553076.067 5935060.047 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_60B24526-B002-4994-B646-967B81439498" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553076.067 5935060.047 553066.392 5935052.049 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A37453BE-3D23-4B8C-A9B7-F26427F7AA08" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553066.392 5935052.049 553063.478 5935049.314 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DE7432C9-5D07-4FD3-9387-FB3EB9C81572" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553063.478 5935049.314 553056.895 5935043.264 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_70F3619D-46FA-47E5-AE8B-8922BFC82700" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553056.895 5935043.264 553052.79 5935039.048 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AF329565-E597-4068-9FB1-6BB576BD5949" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553052.79 5935039.048 553048.283 5935034.119 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F3C7FC80-9232-4E45-BE23-8353AF96DE30" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553048.283 5935034.119 553047.724 5935033.506 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DC9003A5-AE5F-4DA0-87F1-B7FABE1301DF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553047.724 5935033.506 553043.308 5935028.187 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BD3A7CEF-6229-444D-B0A2-653319A0917B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553043.308 5935028.187 553041.991 5935026.6 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7292722E-5AFC-4D3E-AE41-CC4F388925CB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553041.991 5935026.6 553039.242 5935022.958 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_480ECE0D-71A2-4068-8B76-B0C5E46C691B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553039.242 5935022.958 553034.177 5935016.092 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6B2D0F04-A120-4232-B0AF-A390D7B32830" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553034.177 5935016.092 553032.5 5935013.818 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_85548EB4-9C90-417B-97EE-875C557BF07C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553032.5 5935013.818 553028.178 5935007.962 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BC09C520-0F2B-45BC-9B76-BA44A7BD2A29" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553028.178 5935007.962 553024.258 5935001.607 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DCEE1ED5-F087-4BB3-91CC-389266D903E4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553024.258 5935001.607 553023.548 5935000.455 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0843497C-5062-4326-9B80-9D18A0983968" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553023.548 5935000.455 553021.17 5934996.737 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8F3EA274-2EA9-4D83-9735-CEE59EB6D27E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553021.17 5934996.737 553019.857 5934994.632 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_27807D98-B61F-4145-95B6-F2D3870D22EB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553019.857 5934994.632 553018.771 5934992.782 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_317E6D89-FD5B-4F9A-99D0-2F2A6A65B24E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553018.771 5934992.782 553018.342 5934991.971 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C966B0CE-CD50-4E47-A883-1E63BB5CD22E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553018.342 5934991.971 553017.322 5934990.798 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_2518195F-CC85-42FA-BDBF-33158FCCCBDB" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553017.322 5934990.798 553016.486967286 5934989.75996394 553015.535 5934988.828 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_ABCAEFA2-A746-416C-B26E-C22B0A987D59" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553015.535 5934988.828 553014.366585597 5934987.49655946 553013.109 5934986.249 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_6BFE852D-3DA7-42BF-931E-26B6BF30EC90" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553013.109 5934986.249 553012.831989058 5934985.93598812 553012.543 5934985.634 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_7C4AF4E1-FE5A-43B4-844D-91FFADFF0C8C" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553012.543 5934985.634 553012.095874911 5934985.20788719 553011.626 5934984.807 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_3798AFBC-CFEF-4167-9D9B-B00352906240" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553011.626 5934984.807 553009.803215356 5934983.40416606 553007.983 5934981.998 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_9468E9B0-713E-4DD6-87BE-16826A5F54B4" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553007.983 5934981.998 553007.206699469 5934981.38877603 553006.401 5934980.819 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_30D06D9E-AF7E-4B8C-8915-97CC9E1FC18F" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553006.401 5934980.819 553005.261069704 5934980.02704046 553004.008 5934979.43 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_DD21F0B0-3D1A-449E-8260-CE6F3A68CBDA" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553004.008 5934979.43 553002.415580336 5934978.63379626 553000.805 5934977.875 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_10F26BB6-6F4A-4843-A744-5C10392A6D89" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553000.805 5934977.875 552998.753979175 5934977.11199307 552996.655 5934976.493 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_C42B2FB7-2D00-4875-A353-6C1D0D77345C" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">552996.655 5934976.493 552994.788164679 5934975.98703473 552992.876 5934975.696 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_79916D27-796C-4F12-A44E-AE7FFCDE5FFD" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552992.876 5934975.696 552992.412 5934969.734 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_472CB508-1493-4B6D-AC01-EA22BFFBCEF5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552992.412 5934969.734 552991.593 5934959.206 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9DBB936A-713F-4E4B-AEC9-77EE2E97A607" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552991.593 5934959.206 552990.765 5934959.302 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_81F7ED20-CCC8-4280-9D57-850DD348FE5B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552990.765 5934959.302 552975.051 5934961.949 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BF478E8B-EE41-4196-961C-3C43A554E18D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552975.051 5934961.949 552972.303 5934962.412 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9DC6598F-55DD-49E9-B278-87281F377A7C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552972.303 5934962.412 552959.196 5934967.009 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3DF32F52-0829-4EB7-953D-32EE46FD2E3C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552959.196 5934967.009 552952.682 5934970.191 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_71833EAA-4503-43B9-9A83-CFA691990789" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552952.682 5934970.191 552951.709 5934976.337 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_75C2406A-C861-4999-BFA2-5F0C25F3F037" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552951.709 5934976.337 552930.917 5934992.515 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_669D3CEE-6B74-4084-97C2-69312B75BCAB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552930.917 5934992.515 552908.181 5935011.64 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_79F05D88-544D-4D35-AF7D-AA61DEA3BA53" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552908.181 5935011.64 552894.064 5935023.516 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A8965FCA-A7B4-402E-A760-2B40330F068D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552894.064 5935023.516 552881.056 5935034.204 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B0E9A432-2679-478B-AA2C-E3C94596AE66" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552881.056 5935034.204 552878.613 5935031.589 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_11161FA9-6227-4893-BC9C-9E2CA9EB8356" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552878.613 5935031.589 552886.39 5935018.797 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_363E73D2-29D9-4893-90A0-F01FCE979078" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552886.39 5935018.797 552887.269 5935017.351 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2A6BE58E-63AC-476F-859D-CE37658E8290" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552887.269 5935017.351 552907.864 5934989.746 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DB98D24A-D523-4F4A-A2F9-AFE305490F53" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552907.864 5934989.746 552934.511 5934957.536 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BB50C8D4-5774-4161-BF9D-1FA78CB2A079" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552934.511 5934957.536 552938.442 5934955.399 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_249F7F06-F7DD-4EF4-86BB-81C7265D3499" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552938.442 5934955.399 552966.031 5934941.015 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D4A0FD39-1D4D-4CE3-A458-1A236DF4D38F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552966.031 5934941.015 552968.262 5934939.851 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0EC24460-0DF9-4C11-BFD3-22E7F0ED9C75" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552968.262 5934939.851 552967.852 5934938.957 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EE99F7EF-CF46-426D-8453-47F25FCA8158" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552967.852 5934938.957 552989.494 5934927.822 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_29D0D069-AF30-4D28-A60E-6E551621DA6E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">552989.494 5934927.822 553010.063 5934906.446 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_47D9EA1C-DE6B-4B6A-BC79-9077198AA4E5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553010.063 5934906.446 553017.992 5934898.206 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_39C44AE3-AC44-4F8E-8085-F787EA7A0F58" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553017.992 5934898.206 553022.536 5934896.272 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_63778DDE-18DA-446F-8300-9175B3D18AD7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553022.536 5934896.272 553028.056 5934893.921 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3446C126-0A21-48EC-847E-563AE8C8C81F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553028.056 5934893.921 553038.198 5934891.021 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DF14CE9C-1712-4153-85FA-6A1846E979A6" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553038.198 5934891.021 553062.516 5934884.016 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_92A6E209-882B-44F5-BEB8-BF800432E65B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553062.516 5934884.016 553063.87 5934883.291 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C1616052-DED1-4506-9C9E-755B9F9B2164" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553063.87 5934883.291 553067.516 5934881.336 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_94488D97-CD10-4DDC-BA50-C609B04BAF18" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553067.516 5934881.336 553081.41 5934873.453 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0AD3F223-275E-41D6-904F-C00F0913094C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553081.41 5934873.453 553093.69 5934863.988 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7591CEC0-7B98-46E8-B73E-D7543793FF08" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553093.69 5934863.988 553095.905 5934861.528 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_FBE6D669-4FC4-4209-88A3-3D2FFFCF167E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553095.905 5934861.528 553102.972 5934853.676 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BCD3E174-9355-42F4-9F69-EEF6A4B908C8" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553102.972 5934853.676 553106.117 5934849.751 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8E41F8EB-0C96-4409-AB66-DD086772445D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553106.117 5934849.751 553109.856 5934845.087 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2767736B-BE8D-49E2-9432-3FB2AC1078B0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553109.856 5934845.087 553117.248 5934835.864 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CFF31FD3-DCF1-4FFF-B0D4-2B9EAF8315F2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553117.248 5934835.864 553122.243 5934829.63 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3400E56F-997A-464F-9873-24DE93347121" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553122.243 5934829.63 553131.412 5934817.635 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4B371C12-4E45-498E-B6A1-4B7181942C76" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553131.412 5934817.635 553134.486 5934808.353 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_942E6F84-E951-4FF2-9828-050BE5E63F0D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553134.486 5934808.353 553136.295 5934802.084 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7EC7BA3C-5A0C-4711-8BCB-E1CF61AE4B6E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553136.295 5934802.084 553138.955 5934791.537 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EDA2DD0F-4BDD-4430-9456-9A34646C8F99" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553138.955 5934791.537 553140.162 5934783.671 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6192CFF0-ADF3-4530-8DC5-8CDCE4D1064F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553140.162 5934783.671 553144.105 5934783.032 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4A431F5D-876B-4941-85E5-DB658D77B2F2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553144.105 5934783.032 553150.043 5934795.408 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9259039E-8528-42D0-9CE2-A574C6C7877C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553150.043 5934795.408 553151.82 5934798.792 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E3324A51-0529-40B4-8CB6-6D6FEA8C59DE" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553151.82 5934798.792 553153.371 5934801.55 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3ADF1C40-26CD-40F1-92C9-95EC16794677" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553153.371 5934801.55 553154.396 5934802.93 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B4ADE61D-C9C8-4E98-AD74-7C4F044FCC57" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553154.396 5934802.93 553155.594 5934804.929 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B46AFF82-E5B4-4DD1-B9A6-117E5912280E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553155.594 5934804.929 553161.502 5934811.869 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_15B95ED5-A913-4A08-BA6B-884D1D58E0F9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553161.502 5934811.869 553167.562 5934818.16 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_11A55D37-3EBD-43F1-826F-D2191DF0AE9F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553167.562 5934818.16 553169.858 5934820.95 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_966B0FC1-4392-4F4F-94A8-D37396DD2D1E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553169.858 5934820.95 553171.993 5934823.814 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BB423ABD-3C6D-4935-ABF8-EA8E79BE9911" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553171.993 5934823.814 553172.098 5934823.996 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B9048F51-3D8C-4E92-AB15-9C7066FB25DA" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553172.098 5934823.996 553173.229 5934825.976 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_37966C79-00AB-4B05-A90D-D2E0FCCD1DD4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553173.229 5934825.976 553177.284 5934835.532 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F419AE13-BB01-4398-9C74-325EAA223C1F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553177.284 5934835.532 553178.832 5934839.171 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DF73414D-971E-45D5-A6CF-D28B6FF81311" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553178.832 5934839.171 553179.8241 5934841.5919 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_94B6E39E-C80C-48EA-A49F-8422D059F048" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553179.8241 5934841.5919 553180.481 5934842.873 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9CB0F3E6-7C5E-4ABD-904E-8E5FA41320DD" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553180.481 5934842.873 553181.173 5934842.468 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D5A12360-2AE5-4943-B302-9A86568F0D4A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553181.173 5934842.468 553182.22 5934842.051 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2DAA7284-6F82-4664-9087-B56BEECC40EA" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553182.22 5934842.051 553184.536 5934841.337 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_14E6BA2F-FBEC-4715-BC9B-4580F96DFD31" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553184.536 5934841.337 553190.987 5934840.533 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_24ED3996-0366-4C77-AEBF-762B24E371D3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553190.987 5934840.533 553199.801 5934838.653 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EDFA8F1E-03EB-4D19-A7B5-FF11697A4369" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553199.801 5934838.653 553200.412 5934838.509 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_11FC6693-71E6-4701-8C20-CFCC0C02294E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553200.412 5934838.509 553201.016 5934838.666 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F9F9F3B5-1661-41A3-B311-77350A9CF40B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553201.016 5934838.666 553202.629 5934839.931 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A89F30CE-60BE-48EE-B6D6-4EE7BB9216AD" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553202.629 5934839.931 553203.035 5934840.252 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_31C881DC-842A-45DB-BE93-D874E008AE7C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553203.035 5934840.252 553203.114 5934840.748 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_AF0C4072-3934-4552-9CE6-64ABA0A2A0DF" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553203.114 5934840.748 553199.739943437 5934848.44813307 553196.537 5934856.221 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_84C07913-EB75-4EB8-8350-C7966720C9DB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553196.537 5934856.221 553195.395 5934860.083 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_A69D5785-69C2-47C7-A2C1-20EFFC527FD7" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553195.395 5934860.083 553194.323873055 5934862.71730787 553193.227 5934865.341 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_A6905DB1-8F5B-4B6D-B0D0-D156C6958061" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553193.227 5934865.341 553192.743060412 5934866.49586231 553192.221 5934867.634 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_60F291E3-5812-4D49-A1DD-62DC7D44EF6A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.221 5934867.634 553194.051 5934869.626 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_35AD4C0B-4BD2-4DC8-BD6F-27332BEDF606" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553194.051 5934869.626 553196.342 5934872.12 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_3FCA637F-4C32-4AC9-BFE5-F30F66827B09" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553196.342 5934872.12 553203.957362031 5934878.60729685 553211.811 5934884.804 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F496A61C-0299-422E-A359-3C1FABD16D72" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553211.811 5934884.804 553221.753 5934891.379 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E510992E-A042-480F-90D8-6B0F30ACFDF2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553221.753 5934891.379 553223.027 5934892.152 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_74F2F9DA-85ED-41A0-85CF-5341AEA4CC59" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553223.027 5934892.152 553225.005 5934893.858 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3D3A1804-47F0-46F3-9F08-4148F846B537" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553225.005 5934893.858 553225.945 5934895.043 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_BE432D2B-D8DE-48F0-BC02-AD8EB5AA5645" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553225.945 5934895.043 553229.571 5934901.985 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EC53BC74-56CA-421C-9641-B97F413C22B7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553229.571 5934901.985 553232.334 5934905.994 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7C1CECFB-41CD-4638-933C-2C6773CA19EC" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553232.334 5934905.994 553240.721 5934917.011 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_69478BF4-E23F-45D4-A605-91CFB8CD9392" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553240.721 5934917.011 553241.738 5934918.349 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7B052C7F-8D79-4FAF-BD81-ECE14443E7D7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553241.738 5934918.349 553251.227 5934930.652 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EFE83427-8467-4E6B-BA2F-CBC6F542C39E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553251.227 5934930.652 553259.312 5934940.966 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_19B9E9A9-AE7A-4B30-B68D-6664A2D747F9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553259.312 5934940.966 553262.451 5934939.088 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6E31C0BE-9E3A-484D-AC1E-309E48DEC38E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553262.451 5934939.088 553277.975 5934949.65 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2E0E3B09-470C-462A-AC4A-14D1D0D823E5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553277.975 5934949.65 553281.477 5934952.138 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C825039D-FBAE-4D5C-9E3B-06075B875D4B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553281.477 5934952.138 553296.465 5934964.008 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_361DBBE0-002E-47E8-95D9-78CBBC69DA1A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553296.465 5934964.008 553297.422 5934963.64 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_73B84902-2503-46E2-87D4-7F34F4D04DEB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553297.422 5934963.64 553302.95 5934970.42 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_D8044779-B1F2-4981-8C78-AE24B0B12D33" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553302.95 5934970.42 553289.676 5934978.967 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6172AD87-1AB9-4E93-BCD0-82F284CB5403" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553289.676 5934978.967 553261.183 5934997.286 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_08D34F73-A29C-42A1-86D9-83DB7EBD6651" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553261.183 5934997.286 553264.006 5935001.415 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6E92D3D9-FB6B-42EC-AB23-4A1FEE90427C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553264.006 5935001.415 553274.278 5935016.443 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_93709463-9B74-4ADB-985D-C5C36A1DAFDC" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553274.278 5935016.443 553299.972 5935051.125 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_29FFE063-0A10-4255-9D5E-2812E0035144" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553299.972 5935051.125 553294.9752 5935054.3542 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E59E3766-0078-4CB1-AC51-40D187855B4D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553294.9752 5935054.3542 553258.2483 5935066.594 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4BB9AD9E-33EB-4697-B1DD-1489A81A24C0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553258.2483 5935066.594 553242.1183 5935067.0238 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C42AFBA1-D567-4628-A504-476F45B35286" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553242.1183 5935067.0238 553230.74 5935070.32 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_632B30EE-8D5C-4F29-84CF-944212BFE502" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553230.74 5935070.32 553225.953 5935070.404 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E5DDDFD4-465B-45F1-9C26-98C07CA5B8E5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553225.953 5935070.404 553209.094 5935069.728 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_1B1786D0-B139-408B-AE6D-9BEAE268DFF3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553209.094 5935069.728 553190.206 5935069.693 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_80615239-9A0A-4B6B-A513-B48992855713" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553190.206 5935069.693 553190.199 5935069.68 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A151E832-1348-4028-A2B9-5FCE7DC71E5C" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553190.199 5935069.68 553185.008 5935069.68 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_59A0A29B-8C48-47F0-894E-F2C4A303DAAD" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553185.008 5935069.68 553184.083 5935078.629 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:gebietsArt>2400</xplan:gebietsArt>
    </xplan:SO_Gebiet>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_dd4ccc4b-e64a-46c8-b2a5-198f95a7aaef">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552995.018 5934910.539</gml:lowerCorner>
          <gml:upperCorner>553018.757 5934933.426</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_75B3D728-2927-4AF1-A933-43DE6E8097CC" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553003.501 5934933.426 552995.018 5934924.939 553009.487 5934910.539 
553018.757 5934919.832 553011.524 5934927.044 553010.722 5934926.24 
553003.501 5934933.426 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_981e1024-453b-451e-bc61-e27cbf632243">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553078.285 5934878.977</gml:lowerCorner>
          <gml:upperCorner>553096.924 5934896.586</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_2F2C53A1-9491-4500-92E5-C6091C084EC0" srsName="EPSG:25832">
          <gml:posList>553096.924 5934887.963 553090.645 5934896.586 553078.285 5934887.597 
553084.553 5934878.977 553096.924 5934887.963 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_7f5e7a57-6a7c-4188-875d-c5baa1c12e96">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553171.904 5934942.663</gml:lowerCorner>
          <gml:upperCorner>553185.035 5934955.248</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_ACB059E4-FD73-4FE2-BD69-FD96EE5B8059" srsName="EPSG:25832">
          <gml:posList>553184.403 5934947.356 553182.789 5934949.876 553185.035 5934951.313 
553182.869 5934954.699 553180.638 5934953.273 553179.39 5934955.248 
553177.876 5934954.81 553171.904 5934950.732 553177.07 5934942.663 
553177.7 5934943.066 553184.404 5934947.354 553184.403 5934947.356 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_5b29d92a-0faf-47d0-aa23-d11e5ffc595b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553157.026 5934827.453</gml:lowerCorner>
          <gml:upperCorner>553167.304 5934840.559</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_b23b7c5f-aafd-4643-8145-10406d444814" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_61DE7451-D259-4D47-A1B3-8001E55039C6" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553167.304 5934838.71 553159.552 5934840.559 553157.026 5934829.268 
553164.617 5934827.453 553167.304 5934838.71 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_b23b7c5f-aafd-4643-8145-10406d444814">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553162.066 5934834.157</gml:lowerCorner>
          <gml:upperCorner>553162.066 5934834.157</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5b29d92a-0faf-47d0-aa23-d11e5ffc595b" />
      <xplan:position>
        <gml:Point gml:id="Gml_3311694F-346D-4443-B3F4-D5BFBB7767E6" srsName="EPSG:25832">
          <gml:pos>553162.066 5934834.157</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_9960dc2b-775f-4303-b6c4-0d02370ed3f2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552996.101 5934938.648</gml:lowerCorner>
          <gml:upperCorner>553011.233 5934949.324</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_C4E07C38-3476-46D6-B395-4E9E04ED725D" srsName="EPSG:25832">
          <gml:posList>553011.233 5934944.058 552999.207 5934949.324 552996.101 5934942.236 
553002.185 5934939.574 553002.92 5934941.249 553008.862 5934938.648 
553011.233 5934944.058 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_4e16896b-4dd9-4e43-910b-69b9bbeabb8d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553276.21 5934954.073</gml:lowerCorner>
          <gml:upperCorner>553294.915 5934971.548</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D09AEB38-F92C-41BA-966C-33A8A49B81C3" srsName="EPSG:25832">
          <gml:posList>553279.581 5934954.073 553284.996 5934961.34 553288.519 5934958.716 
553294.915 5934967.306 553289.215 5934971.548 553282.821 5934962.956 
553281.621 5934963.851 553276.21 5934956.585 553279.581 5934954.073 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_1d2e4da2-cf09-4aaf-a436-2cfe88838e11">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553209.946 5935016.296</gml:lowerCorner>
          <gml:upperCorner>553225.674 5935035.876</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0d7e3dda-291e-4f00-8de9-6265e62020aa" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_03D7E15F-AC96-4F9A-80BE-8E01321FB60E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553225.674 5935034.591 553221.168 5935034.637 553221.18 5935035.815 
553214.929 5935035.876 553214.917 5935034.662 553210.12 5935034.662 
553209.946 5935016.465 553225.48 5935016.296 553225.674 5935034.591 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0d7e3dda-291e-4f00-8de9-6265e62020aa">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553217.329 5935025.131</gml:lowerCorner>
          <gml:upperCorner>553217.329 5935025.131</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1d2e4da2-cf09-4aaf-a436-2cfe88838e11" />
      <xplan:position>
        <gml:Point gml:id="Gml_EAFCF283-EBC2-49E7-AF65-77F825AAE04D" srsName="EPSG:25832">
          <gml:pos>553217.329 5935025.131</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_500e7a25-75f5-47f2-b627-fe1dbf21d94e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553117.951 5934927.222</gml:lowerCorner>
          <gml:upperCorner>553127.533 5934934.627</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_F3788C2F-448B-4511-AF8C-990BC5705CBB" srsName="EPSG:25832">
          <gml:posList>553126.898 5934927.222 553127.533 5934933.759 553118.585 5934934.627 
553117.951 5934928.092 553126.898 5934927.222 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_154500ed-c813-4280-9ee4-2add13b3bc18">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553012.997 5934901.179</gml:lowerCorner>
          <gml:upperCorner>553029.294 5934915.092</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_0E87370A-BC86-444F-8E41-264B7AA4F898" srsName="EPSG:25832">
          <gml:posList>553026.488 5934911.029 553026.911 5934911.448 553023.296 5934915.092 
553019.399 5934911.225 553017.496 5934913.142 553012.997 5934908.676 
553018.247 5934903.39 553019.138 5934904.274 553022.207 5934901.179 
553029.294 5934908.199 553026.488 5934911.029 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_9b9acc5d-c6f3-4095-9d22-8a8ab0e7ab47">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553127.577 5934900.81</gml:lowerCorner>
          <gml:upperCorner>553160.29 5934917.59</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_BF17E379-05D8-42E2-8E29-3804EB308D0E" srsName="EPSG:25832">
          <gml:posList>553160.29 5934917.59 553157.67 5934916.321 553153.775 5934916.387 
553149.946 5934915.163 553145.83 5934913.553 553143.986 5934912.266 
553134.78 5934905.839 553133.903 5934905.226 553130.58 5934902.906 
553129.139 5934901.9 553127.577 5934900.81 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_6774fa67-b62e-497c-a1f2-aef96c88d25a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553104.658 5934845.087</gml:lowerCorner>
          <gml:upperCorner>553123.686 5934862.087</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_db233e36-e8df-44df-b861-3f37c8a798b1" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_1D264FC5-0354-4128-9F3C-E298CC34D145" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553118.354 5934862.087 553115.87 5934860.506 553115.221 5934861.52 
553109.541 5934857.883 553110.179 5934856.888 553107.587 5934855.229 
553104.658 5934853.355 553105.91 5934851.398 553106.117 5934849.751 
553109.856 5934845.087 553112.598 5934846.817 553116.052 5934848.996 
553116.231 5934848.715 553120.287 5934851.279 553120.17 5934851.464 
553123.686 5934853.692 553118.354 5934862.087 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_db233e36-e8df-44df-b861-3f37c8a798b1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553113.894 5934853.448</gml:lowerCorner>
          <gml:upperCorner>553113.894 5934853.448</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_6774fa67-b62e-497c-a1f2-aef96c88d25a" />
      <xplan:position>
        <gml:Point gml:id="Gml_71642D01-D480-44A5-84A1-1DBC71861FA0" srsName="EPSG:25832">
          <gml:pos>553113.894 5934853.448</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_7a78a1b8-10df-4ce6-9195-d82aad5f29aa">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552992.007 5934906.446</gml:lowerCorner>
          <gml:upperCorner>553025.618 5934940.16</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_80FC87D5-DDBE-4BB7-AC00-E2287B929560" srsName="EPSG:25832">
          <gml:posList>553010.063 5934906.446 553023.564 5934919.662 553025.618 5934921.673 
553013.438 5934930.777 553002.666 5934938.825 552992.007 5934940.16 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_a0dd913d-29bc-40c9-a8a4-95627921add8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553196.179 5934878.096</gml:lowerCorner>
          <gml:upperCorner>553209.623 5934891.088</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_602B135A-E5F3-4372-82B5-E5BA186E0214" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553209.623 5934884.266 553204.637 5934891.088 553196.179 5934884.905 
553201.156 5934878.096 553209.623 5934884.266 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_9c407f91-f0b1-46f8-b698-6a3ca0e03b67">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553093.69 5934857.416</gml:lowerCorner>
          <gml:upperCorner>553112.376 5934871.302</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_A531BEE6-B4CF-4C7E-BE61-58508240A025" srsName="EPSG:25832">
          <gml:posList>553095.905 5934861.528 553099.041 5934863.912 553101.537 5934860.203 
553102.187 5934860.641 553104.358 5934857.416 553112.376 5934862.819 
553110.092 5934866.212 553110.878 5934866.742 553108.3 5934870.577 
553106.09 5934869.092 553104.608 5934871.302 553101.008 5934868.891 
553093.69 5934863.988 553095.905 5934861.528 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_58523c86-9eb6-4391-ab00-653893398968">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553053.654 5934883.291</gml:lowerCorner>
          <gml:upperCorner>553080.833 5934938.839</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_47CB9C5B-ED7B-4ED7-863E-72E3D8956134" srsName="EPSG:25832">
          <gml:posList>553063.87 5934883.291 553078.21 5934898.615 553072.83 5934900.369 
553060.597 5934905.059 553053.654 5934907.891 553053.761 5934908.007 
553068.183 5934923.751 553075.161 5934931.367 553080.833 5934938.839 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_8797e25c-00e4-4eb2-b1b2-06aa6cb153b0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553236.647 5934939.243</gml:lowerCorner>
          <gml:upperCorner>553269.106 5934973.379</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C91C08DA-80F3-402E-A9A7-307B349623A5" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553268.584 5934958.094 553264.378 5934964.91 553269.106 5934967.848 
553265.704 5934973.379 553260.284 5934970.153 553260.688 5934969.288 
553255.844 5934966.062 553252.13 5934965.027 553245.202 5934961.035 
553236.647 5934950.762 553250.479 5934939.243 553255.315 5934945.051 
553246.975 5934951.996 553247.8 5934953.299 553255.685 5934957.842 
553260.915 5934949.337 553267.488 5934953.543 553265.989 5934956.481 
553268.584 5934958.094 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_a25f0efa-53cd-449f-80ac-0a0fa35554f4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553148.487 5934902.753</gml:lowerCorner>
          <gml:upperCorner>553164.317 5934916.05</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_82a414e3-2862-47bb-b0a5-40d584a94abc" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_975C3E58-75E7-4092-B936-86F90DB0AF72" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553164.317 5934909.971 553161.137 5934916.05 553156.598 5934915.274 
553148.487 5934910.024 553153.216 5934902.753 553164.317 5934909.971 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_82a414e3-2862-47bb-b0a5-40d584a94abc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553156.457 5934909.964</gml:lowerCorner>
          <gml:upperCorner>553156.457 5934909.964</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_a25f0efa-53cd-449f-80ac-0a0fa35554f4" />
      <xplan:position>
        <gml:Point gml:id="Gml_5C280F7C-E944-4DEC-B849-2400DE09C38E" srsName="EPSG:25832">
          <gml:pos>553156.457 5934909.964</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_035f125a-831a-4ac7-aa90-c489c9b09ca5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553124.226 5934905.036</gml:lowerCorner>
          <gml:upperCorner>553138.337 5934918.721</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_28FDC953-63DD-432D-A8B0-D02A272CFA77" srsName="EPSG:25832">
          <gml:posList>553133.589 5934918.721 553124.226 5934913.382 553128.982 5934905.036 
553132.781 5934907.196 553132.768 5934907.218 553138.337 5934910.394 
553133.589 5934918.721 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_35884c9a-0f85-44f5-abfd-121b06c9db12">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553183.41 5934869.626</gml:lowerCorner>
          <gml:upperCorner>553211.811 5934908.601</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_6d419faf-4d8e-4a72-93fc-e53782b8b2f5" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_3a02f33d-3c42-4150-8fae-0b5b5a521061" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_6a544600-7d53-4f60-94c0-d2579125adcd" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_C4148D19-6EB4-4BE6-BD93-B81DB0E376D0" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_453136FE-A592-4CDE-A151-F84E37EABC15" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_B7929CEC-61DC-4647-BDFF-5BC10DA864E4" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_ECD11191-B2EC-4797-AC3B-3AC3E78E4ABA" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553196.342 5934872.12 553203.957362031 5934878.60729685 553211.811 5934884.804 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_135050DC-7BEF-440C-8E50-EC1AE62CF830" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553211.811 5934884.804 553204.931 5934894.549 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_6CE95851-D748-4A3C-B021-55E33114ED6D" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553204.931 5934894.549 553199.046989233 5934901.73301248 553192.797 5934908.601 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_69FB5C42-8733-4A8D-84BC-055335DCD702" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.797 5934908.601 553192.546 5934908.48 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EC2A6937-3C89-40BF-ADBE-F72786149CA1" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.546 5934908.48 553183.41 5934896.056 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_298DE7F2-6EB8-4FAE-A558-8ED1A1EC7A9D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.41 5934896.056 553185.877 5934886.896 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4D623626-EA7D-448A-B972-08601BF93F94" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553185.877 5934886.896 553187.582 5934883.144 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_38228B9B-3622-45C5-A10D-82BD6BF9C926" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553187.582 5934883.144 553192.234 5934874.11 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A0775020-BAC1-445F-81BA-9E9C0B65D8D2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553192.234 5934874.11 553194.051 5934869.626 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_59A2B65F-8F34-4C09-B9F6-C1D103386653" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553194.051 5934869.626 553196.342 5934872.12 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:Z>1</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_6d419faf-4d8e-4a72-93fc-e53782b8b2f5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553195.586 5934895.159</gml:lowerCorner>
          <gml:upperCorner>553195.586 5934895.159</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_35884c9a-0f85-44f5-abfd-121b06c9db12" />
      <xplan:position>
        <gml:Point gml:id="Gml_9FD3946A-AEC3-44D0-A5EA-6442D2E9747F" srsName="EPSG:25832">
          <gml:pos>553195.586 5934895.159</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_3a02f33d-3c42-4150-8fae-0b5b5a521061">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553192.557 5934883.601</gml:lowerCorner>
          <gml:upperCorner>553192.557 5934883.601</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_35884c9a-0f85-44f5-abfd-121b06c9db12" />
      <xplan:position>
        <gml:Point gml:id="Gml_A04A2560-AC86-449A-8EE7-C52C729EF739" srsName="EPSG:25832">
          <gml:pos>553192.557 5934883.601</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_6a544600-7d53-4f60-94c0-d2579125adcd">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553201.334 5934895.11</gml:lowerCorner>
          <gml:upperCorner>553201.334 5934895.11</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_35884c9a-0f85-44f5-abfd-121b06c9db12" />
      <xplan:position>
        <gml:Point gml:id="Gml_60387AA8-4DD3-47B4-8F53-A7C144A50505" srsName="EPSG:25832">
          <gml:pos>553201.334 5934895.11</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_C4148D19-6EB4-4BE6-BD93-B81DB0E376D0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553195.2856 5934889.4393</gml:lowerCorner>
          <gml:upperCorner>553195.2856 5934889.4393</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_35884c9a-0f85-44f5-abfd-121b06c9db12" />
      <xplan:position>
        <gml:Point gml:id="Gml_A4EE66CB-2695-428E-ACA6-343DF9A947DD" srsName="EPSG:25832">
          <gml:pos>553195.2856 5934889.4393</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_453136FE-A592-4CDE-A151-F84E37EABC15">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553195.2856 5934877.6124</gml:lowerCorner>
          <gml:upperCorner>553195.2856 5934877.6124</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_35884c9a-0f85-44f5-abfd-121b06c9db12" />
      <xplan:position>
        <gml:Point gml:id="Gml_E85A11EA-756F-4042-9545-34C6CE982D06" srsName="EPSG:25832">
          <gml:pos>553195.2856 5934877.6124</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_70564618-c555-4230-96c4-cfe1e3532f4c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553094.811 5934986.48</gml:lowerCorner>
          <gml:upperCorner>553109.578 5935002.028</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_54A21942-8C8D-4243-8117-0A2F970563AB" srsName="EPSG:25832">
          <gml:posList>553109.578 5934992.563 553103.651 5935002.028 553095.979 5934997.224 
553096.848 5934995.833 553094.811 5934994.56 553099.86 5934986.48 
553109.578 5934992.563 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_13cbcfa8-a898-430d-a4f5-a94d19db9f12">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.297 5935060.673</gml:lowerCorner>
          <gml:upperCorner>553173.3 5935069.538</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_cf6ce712-0907-42bb-8e53-d511f4843b8a" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_EAACF3CB-85C2-4A90-9D07-C01C67000ED0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553173.3 5935062.19 553172.286 5935069.538 553159.297 5935067.994 
553160.168 5935060.673 553173.3 5935062.19 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_cf6ce712-0907-42bb-8e53-d511f4843b8a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553165.985 5935065.2</gml:lowerCorner>
          <gml:upperCorner>553165.985 5935065.2</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_13cbcfa8-a898-430d-a4f5-a94d19db9f12" />
      <xplan:position>
        <gml:Point gml:id="Gml_C1B2D7D1-649C-4657-BA0A-B1899BA7950B" srsName="EPSG:25832">
          <gml:pos>553165.985 5935065.2</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_e54bc0d7-55d2-4eb0-8a43-0232ce0c2180">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553130.235 5934859.725</gml:lowerCorner>
          <gml:upperCorner>553144.568 5934873.201</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_69EA1A31-EBF9-4EAA-8AE0-B703EBA85A2A" srsName="EPSG:25832">
          <gml:posList>553139.997 5934873.201 553130.235 5934867.061 553134.851 5934859.725 
553144.568 5934865.934 553139.997 5934873.201 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_86fc1baf-60ad-4b04-bc85-03a8527a9d8d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553032.689 5934896.603</gml:lowerCorner>
          <gml:upperCorner>553045.153 5934909.058</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_820C4D5E-3E27-4095-8176-5F0729B645D7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553045.153 5934903.009 553038.637 5934909.058 553032.689 5934902.653 
553039.204 5934896.603 553045.153 5934903.009 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_b5281e4e-fb49-4fe8-9f96-14f62225cfab">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553177.7 5934937.183</gml:lowerCorner>
          <gml:upperCorner>553193.857 5934951.189</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D7C07F06-BCF7-40C7-A0BD-FE066A4FDE3F" srsName="EPSG:25832">
          <gml:posList>553177.7 5934943.066 553181.468 5934937.183 553186.681 5934940.545 
553186.748 5934940.439 553193.857 5934945.025 553189.882 5934951.189 
553187.507 5934949.495 553189.045 5934947.11 553186.952 5934945.761 
553185.432 5934948.014 553184.403 5934947.356 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_b8d046ab-a466-47aa-9d3e-1b82c14e7cb7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552992.412 5934941.6</gml:lowerCorner>
          <gml:upperCorner>553158.31 5935071.436</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Curve gml:id="Gml_2AB6A7CB-4DC6-47B4-980A-8D67BCB443C9" srsName="EPSG:25832">
          <gml:segments>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553152.776 5934964.124 553153.509 5934962.184 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553153.509 5934962.184 553156.303 5934954.786 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553156.303 5934954.786 553156.585 5934953.467 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553156.585 5934953.467 553158.31 5934945.4 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553158.31 5934945.4 553157.345 5934941.6 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553157.345 5934941.6 553135.533 5934943.913 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553135.533 5934943.913 553125.302 5934944.827 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553125.302 5934944.827 553113.808 5934945.854 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553113.808 5934945.854 553102.888 5934946.829 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553102.888 5934946.829 553089.85 5934947.994 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553089.85 5934947.994 553082.68 5934948.635 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553082.68 5934948.635 553035.989 5934957.607 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553035.989 5934957.607 553017.281 5934964.074 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553017.281 5934964.074 552992.412 5934969.734 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">552992.412 5934969.734 552992.876 5934975.696 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">552992.876 5934975.696 552994.788164679 5934975.98703473 552996.655 5934976.493 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">552996.655 5934976.493 552998.753979175 5934977.11199307 553000.805 5934977.875 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553000.805 5934977.875 553002.415580336 5934978.63379626 553004.008 5934979.43 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553004.008 5934979.43 553005.261069704 5934980.02704046 553006.401 5934980.819 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553006.401 5934980.819 553007.206699469 5934981.38877603 553007.983 5934981.998 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553007.983 5934981.998 553009.803215356 5934983.40416606 553011.626 5934984.807 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553011.626 5934984.807 553012.095874911 5934985.20788719 553012.543 5934985.634 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553012.543 5934985.634 553012.831989058 5934985.93598812 553013.109 5934986.249 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553013.109 5934986.249 553014.366585597 5934987.49655946 553015.535 5934988.828 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553015.535 5934988.828 553016.486967286 5934989.75996394 553017.322 5934990.798 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553017.322 5934990.798 553018.342 5934991.971 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553018.342 5934991.971 553018.771 5934992.782 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553018.771 5934992.782 553019.857 5934994.632 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553019.857 5934994.632 553021.17 5934996.737 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553021.17 5934996.737 553023.548 5935000.455 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553023.548 5935000.455 553024.258 5935001.607 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553024.258 5935001.607 553028.178 5935007.962 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553028.178 5935007.962 553032.5 5935013.818 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553032.5 5935013.818 553034.177 5935016.092 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553034.177 5935016.092 553039.242 5935022.958 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553039.242 5935022.958 553041.991 5935026.6 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553041.991 5935026.6 553043.308 5935028.187 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553043.308 5935028.187 553047.724 5935033.506 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553047.724 5935033.506 553048.283 5935034.119 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553048.283 5935034.119 553052.79 5935039.048 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553052.79 5935039.048 553056.895 5935043.264 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553056.895 5935043.264 553063.478 5935049.314 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553063.478 5935049.314 553066.392 5935052.049 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553066.392 5935052.049 553076.067 5935060.047 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553076.067 5935060.047 553078.363 5935061.752 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553078.363 5935061.752 553082.896 5935064.915 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553082.896 5935064.915 553083.305 5935065.342 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553083.305 5935065.342 553084.065 5935065.746 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553084.065 5935065.746 553086.919 5935066.977 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553086.919 5935066.977 553092.331 5935069.18 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553092.331 5935069.18 553095.689 5935069.942 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553095.689 5935069.942 553097.811 5935070.423 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553097.811 5935070.423 553103.762 5935071.226 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553103.762 5935071.226 553105.475 5935071.436 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553105.475 5935071.436 553110.286565974 5935068.68207358 553115.737 5935069.696 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553115.737 5935069.696 553117.52 5935066.961 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553117.52 5935066.961 553117.936 5935059.163 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553117.936 5935059.163 553116.587 5935049.381 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553116.587 5935049.381 553113.536 5935040.973 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553113.536 5935040.973 553110.462 5935038.171 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553110.462 5935038.171 553103.983 5935031.706 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553103.983 5935031.706 553102.218 5935027.505 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553102.218 5935027.505 553101.45 5935025.676 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553101.45 5935025.676 553101.477 5935025.162 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553101.477 5935025.162 553101.782 5935019.351 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553101.782 5935019.351 553102.798 5935017.826 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553102.798 5935017.826 553107.768 5935011.213 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553107.768 5935011.213 553108.126 5935010.891 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553108.126 5935010.891 553115.575 5935004.177 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553115.575 5935004.177 553117.725 5935002.984 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553117.725 5935002.984 553129.061 5934996.69 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553129.061 5934996.69 553135.473 5934992.573 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553135.473 5934992.573 553139.784 5934987.18 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553139.784 5934987.18 553142.02 5934983.676 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553142.02 5934983.676 553149.925 5934971.29 </gml:posList>
            </gml:LineStringSegment>
          </gml:segments>
        </gml:Curve>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_757fd155-8cd4-4eca-90f6-5d67b198ed78">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553108.223 5935015.277</gml:lowerCorner>
          <gml:upperCorner>553134.584 5935037.627</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_14CD674F-CA1A-4500-9E4C-C371F101FED6" srsName="EPSG:25832">
          <gml:posList>553134.584 5935025.709 553128.441 5935037.627 553124.052 5935035.315 
553124.67 5935034.14 553108.223 5935025.487 553109.696 5935022.669 
553108.842 5935022.223 553110.606 5935018.835 553112.106 5935019.615 
553114.354 5935015.277 553134.584 5935025.709 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_37e5029c-a8d3-4d65-a033-b3d80ade0aaf">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553104.658 5934845.087</gml:lowerCorner>
          <gml:upperCorner>553123.686 5934862.087</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_B651EFF8-5236-4B45-B6EA-BE6FEB1631D5" srsName="EPSG:25832">
          <gml:posList>553105.91 5934851.398 553106.117 5934849.751 553109.856 5934845.087 
553112.598 5934846.817 553116.052 5934848.996 553116.231 5934848.715 
553120.287 5934851.279 553120.17 5934851.464 553123.686 5934853.692 
553118.354 5934862.087 553115.87 5934860.506 553115.221 5934861.52 
553109.541 5934857.883 553110.179 5934856.888 553107.587 5934855.229 
553104.658 5934853.355 553105.91 5934851.398 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_11b47fe4-7623-4d1b-81f5-3bb1efddc270">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.066 5934978.376</gml:lowerCorner>
          <gml:upperCorner>553172.55 5935014.151</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_8380CA16-E305-4DCE-A487-D7A80FD6FD9E" srsName="EPSG:25832">
          <gml:posList>553172.55 5935012.943 553164.132 5935014.151 553159.066 5934979.522 
553167.589 5934978.376 553172.55 5935012.943 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_52e20261-5e96-4d5c-8f5d-ab5ec5ae9534">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553090.619 5934869.666</gml:lowerCorner>
          <gml:upperCorner>553103.697 5934882.76</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_9BFEAE65-A34D-4559-98EE-5093741121BE" srsName="EPSG:25832">
          <gml:posList>553096.093 5934869.666 553103.697 5934875.109 553098.222 5934882.76 
553090.619 5934877.322 553096.093 5934869.666 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_9688f1ca-47dd-4296-9ddf-0e16dd96d4c0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553033.842 5934922.39</gml:lowerCorner>
          <gml:upperCorner>553048.021 5934935.327</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_958BD554-6ED0-41B4-99F5-52BDE9F58126" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553048.021 5934929.604 553042.431 5934935.327 553033.842 5934926.908 
553035.892 5934924.867 553038.281 5934922.39 553041.845 5934925.872 
553043.005 5934924.688 553048.021 5934929.604 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_2c0030e0-10c1-4460-a36e-3a2b8196a166">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.972 5934781.621</gml:lowerCorner>
          <gml:upperCorner>553177.284 5934868.755</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_cb6eb28a-be08-405a-940c-e2a01145b1a3" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_1844faea-06c5-496a-9290-c7a5d6ace885" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_CB9D1DB9-74B2-4783-AF03-DFC4732D8AB0" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_563D32CC-3153-4396-84DD-0AE962ADF652" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_1665313D-A393-4EC6-A845-D8647C4FE381" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553128.511 5934865.976 553126.516 5934868.755 553115.221 5934861.52 
553109.541 5934857.883 553102.972 5934853.676 553106.117 5934849.751 
553109.856 5934845.087 553117.248 5934835.864 553122.243 5934829.63 
553131.412 5934817.635 553134.486 5934808.353 553136.295 5934802.084 
553138.955 5934791.537 553140.162 5934783.671 553143.428 5934781.621 
553144.105 5934783.032 553150.043 5934795.408 553151.82 5934798.792 
553153.371 5934801.55 553154.396 5934802.93 553155.594 5934804.929 
553161.502 5934811.869 553167.562 5934818.16 553169.858 5934820.95 
553171.993 5934823.814 553172.098 5934823.996 553173.229 5934825.976 
553177.284 5934835.532 553177.11 5934837.379 553176.759 5934840.637 
553175.517 5934842.158 553170.138 5934846.811 553164.913 5934850.241 
553156.586 5934856.672 553156.169 5934856.994 553152.349 5934861.958 
553149.943 5934865.779 553135.335 5934856.473 553128.511 5934865.976 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_cb6eb28a-be08-405a-940c-e2a01145b1a3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553148.4071 5934839.416</gml:lowerCorner>
          <gml:upperCorner>553148.4071 5934839.416</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_2c0030e0-10c1-4460-a36e-3a2b8196a166" />
      <xplan:position>
        <gml:Point gml:id="Gml_6C86A4D1-5F6C-49B6-82A6-1EBFAD5A00BE" srsName="EPSG:25832">
          <gml:pos>553148.4071 5934839.416</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_1844faea-06c5-496a-9290-c7a5d6ace885">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553145.966 5934848.459</gml:lowerCorner>
          <gml:upperCorner>553145.966 5934848.459</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_2c0030e0-10c1-4460-a36e-3a2b8196a166" />
      <xplan:position>
        <gml:Point gml:id="Gml_4ECF5498-1ED1-4A5E-9579-E678D61E197A" srsName="EPSG:25832">
          <gml:pos>553145.966 5934848.459</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_CB9D1DB9-74B2-4783-AF03-DFC4732D8AB0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553147.5158 5934844.0436</gml:lowerCorner>
          <gml:upperCorner>553147.5158 5934844.0436</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_2c0030e0-10c1-4460-a36e-3a2b8196a166" />
      <xplan:position>
        <gml:Point gml:id="Gml_74469A82-3E51-410D-A5CB-2D50C78E7D37" srsName="EPSG:25832">
          <gml:pos>553147.5158 5934844.0436</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_563D32CC-3153-4396-84DD-0AE962ADF652">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553153.1779 5934818.9346</gml:lowerCorner>
          <gml:upperCorner>553153.1779 5934818.9346</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_2c0030e0-10c1-4460-a36e-3a2b8196a166" />
      <xplan:position>
        <gml:Point gml:id="Gml_F2D657F3-64AD-458A-89D9-9CDD984462CC" srsName="EPSG:25832">
          <gml:pos>553153.1779 5934818.9346</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_449c6f06-3dfb-474e-88bd-bb31382a877c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553090.619 5934869.666</gml:lowerCorner>
          <gml:upperCorner>553103.697 5934882.76</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_927889a0-067a-45ff-b560-61b58ca331c2" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_8C47A98A-AEF1-4D61-959E-1EB54B719A21" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553103.697 5934875.109 553098.222 5934882.76 553090.619 5934877.322 
553096.093 5934869.666 553103.697 5934875.109 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_927889a0-067a-45ff-b560-61b58ca331c2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553097.158 5934876.214</gml:lowerCorner>
          <gml:upperCorner>553097.158 5934876.214</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_449c6f06-3dfb-474e-88bd-bb31382a877c" />
      <xplan:position>
        <gml:Point gml:id="Gml_4C3FD725-343F-49F4-A1AA-9B42D0D90681" srsName="EPSG:25832">
          <gml:pos>553097.158 5934876.214</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_33024b16-3df2-4165-a075-8015ffdd1199">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552992.007 5934883.291</gml:lowerCorner>
          <gml:upperCorner>553080.833 5934958.72</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ff5076e2-3928-4f22-9a11-3639f06cfb67" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_1747eb3f-6ea0-4321-a43d-718574e7758f" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_2bf9a728-de04-4b5a-b1c6-2fb994b677ae" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_CE455CFB-F0C8-48F4-A339-A529D3468CA7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_9661675F-E10B-40D7-9E2D-515899351025" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_EC464857-8276-465B-AB36-0ACE51B6643F" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553078.21 5934898.615 553072.83 5934900.369 553060.597 5934905.059 
553053.654 5934907.891 553053.761 5934908.007 553068.183 5934923.751 
553075.161 5934931.367 553080.833 5934938.839 553065.873 5934941.559 
553049.298 5934944.855 553041.055 5934946.494 553031.662 5934949.196 
553026.211 5934950.835 553020.569 5934952.53 553016.806 5934953.97 
553013.647 5934954.98 553009.011 5934956.383 553002 5934957.999 
552995.787 5934958.72 552992.007 5934940.16 553002.666 5934938.825 
553013.438 5934930.777 553025.618 5934921.673 553023.564 5934919.662 
553010.063 5934906.446 553017.992 5934898.206 553022.536 5934896.272 
553028.056 5934893.921 553038.198 5934891.021 553062.516 5934884.016 
553063.87 5934883.291 553078.21 5934898.615 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ff5076e2-3928-4f22-9a11-3639f06cfb67">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553041.478 5934920.186</gml:lowerCorner>
          <gml:upperCorner>553041.478 5934920.186</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_33024b16-3df2-4165-a075-8015ffdd1199" />
      <xplan:position>
        <gml:Point gml:id="Gml_8BC93663-6436-4C8B-9717-ED5ED0601533" srsName="EPSG:25832">
          <gml:pos>553041.478 5934920.186</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_1747eb3f-6ea0-4321-a43d-718574e7758f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553034.093 5934920.237</gml:lowerCorner>
          <gml:upperCorner>553034.093 5934920.237</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_33024b16-3df2-4165-a075-8015ffdd1199" />
      <xplan:position>
        <gml:Point gml:id="Gml_8D1BC766-E962-4865-A044-1B2F0DF9CAA1" srsName="EPSG:25832">
          <gml:pos>553034.093 5934920.237</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_2bf9a728-de04-4b5a-b1c6-2fb994b677ae">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553034.4094 5934911.0406</gml:lowerCorner>
          <gml:upperCorner>553034.4094 5934911.0406</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_33024b16-3df2-4165-a075-8015ffdd1199" />
      <xplan:position>
        <gml:Point gml:id="Gml_582366E0-D8BF-4F4D-80C6-19FC76F5167A" srsName="EPSG:25832">
          <gml:pos>553034.4094 5934911.0406</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_CE455CFB-F0C8-48F4-A339-A529D3468CA7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553035.0162 5934915.6649</gml:lowerCorner>
          <gml:upperCorner>553035.0162 5934915.6649</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_33024b16-3df2-4165-a075-8015ffdd1199" />
      <xplan:position>
        <gml:Point gml:id="Gml_695F27EF-423E-44A0-85F6-16F0E9AFA7EB" srsName="EPSG:25832">
          <gml:pos>553035.0162 5934915.6649</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_9661675F-E10B-40D7-9E2D-515899351025">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553052.0819 5934935.4029</gml:lowerCorner>
          <gml:upperCorner>553052.0819 5934935.4029</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_33024b16-3df2-4165-a075-8015ffdd1199" />
      <xplan:position>
        <gml:Point gml:id="Gml_93CFB9E8-8F19-4638-9E01-3B1E43748A93" srsName="EPSG:25832">
          <gml:pos>553052.0819 5934935.4029</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_b5cfb3fd-df49-4c37-88e3-aac6f1e09b63">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553126.405 5935050.175</gml:lowerCorner>
          <gml:upperCorner>553142.444 5935066.249</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_6C104E8E-E543-41C9-BF00-70E2886C9A2A" srsName="EPSG:25832">
          <gml:posList>553140.636 5935066.249 553126.405 5935064.753 553127.895 5935050.175 
553142.444 5935051.673 553140.636 5935066.249 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_69ba8667-931e-4abe-9569-d5d853848a7a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553141.059 5934795.489</gml:lowerCorner>
          <gml:upperCorner>553150.589 5934808.362</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_daf967a8-4877-4b3d-b67c-44c8dad66026" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C874C7D4-4A2B-4C0A-9BC5-17DE6A24D677" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553150.589 5934806.271 553145.394 5934808.362 553141.059 5934797.578 
553146.248 5934795.489 553150.589 5934806.271 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_daf967a8-4877-4b3d-b67c-44c8dad66026">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553145.958 5934801.827</gml:lowerCorner>
          <gml:upperCorner>553145.958 5934801.827</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_69ba8667-931e-4abe-9569-d5d853848a7a" />
      <xplan:position>
        <gml:Point gml:id="Gml_9DEE068E-42FB-4FED-96AE-2B49B0F60EAF" srsName="EPSG:25832">
          <gml:pos>553145.958 5934801.827</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_fa891473-ad07-4bc8-a874-b117b9012a86">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553059.716 5935023.902</gml:lowerCorner>
          <gml:upperCorner>553077.596 5935041.91</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_af1e6a12-7e82-4660-b922-fb3d0f28e741" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_EF487DFF-3FA1-45D1-A91B-5C2BBAF0CDB8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553077.596 5935031.4 553067.607 5935041.91 553059.716 5935034.407 
553069.71 5935023.902 553077.596 5935031.4 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_af1e6a12-7e82-4660-b922-fb3d0f28e741">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553068.657 5935032.905</gml:lowerCorner>
          <gml:upperCorner>553068.657 5935032.905</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_fa891473-ad07-4bc8-a874-b117b9012a86" />
      <xplan:position>
        <gml:Point gml:id="Gml_60862A6A-32A7-4E0F-83C4-89EFDAF2A25D" srsName="EPSG:25832">
          <gml:pos>553068.657 5935032.905</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_1af9518d-3e51-455c-a973-f811393e2ea8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553106.434 5934838.509</gml:lowerCorner>
          <gml:upperCorner>553203.114 5934939.345</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Curve gml:id="Gml_4AF82D4F-326A-4E3F-B76F-EA3EED653026" srsName="EPSG:25832">
          <gml:segments>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553107.603 5934939.345 553139.99 5934936.588 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553139.99 5934936.588 553153.416 5934935.409 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553153.416 5934935.409 553156.212 5934932.322 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553156.212 5934932.322 553157.142 5934922.128 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553157.142 5934922.128 553157.916 5934920.143 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553157.916 5934920.143 553158.107 5934919.517 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553158.107 5934919.517 553160.29 5934917.59 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553160.29 5934917.59 553168.888 5934909.251 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553168.888 5934909.251 553175.612 5934902.116 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553175.612 5934902.116 553177.597 5934900.035 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553177.597 5934900.035 553181.305105664 5934893.70674587 553183.177 5934886.615 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553183.177 5934886.615 553183.625 5934883.814 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553183.625 5934883.814 553185.402 5934881.885 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553185.402 5934881.885 553188.92910789 5934874.81577452 553192.221 5934867.634 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553192.221 5934867.634 553192.743060412 5934866.49586231 553193.227 5934865.341 
</gml:posList>
            </gml:ArcString>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553193.227 5934865.341 553194.323873055 5934862.71730787 553195.395 5934860.083 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553195.395 5934860.083 553196.537 5934856.221 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553196.537 5934856.221 553199.739943437 5934848.44813307 553203.114 5934840.748 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553203.114 5934840.748 553203.035 5934840.252 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553203.035 5934840.252 553202.629 5934839.931 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553202.629 5934839.931 553201.016 5934838.666 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553201.016 5934838.666 553200.412 5934838.509 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553200.412 5934838.509 553199.801 5934838.653 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553199.801 5934838.653 553190.987 5934840.533 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553190.987 5934840.533 553184.536 5934841.337 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553184.536 5934841.337 553182.22 5934842.051 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553182.22 5934842.051 553181.173 5934842.468 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553181.173 5934842.468 553180.481 5934842.873 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553180.481 5934842.873 553180.775 5934843.278 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553180.775 5934843.278 553172.603 5934848.851 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553172.603 5934848.851 553158.242 5934860.856 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553158.242 5934860.856 553156.849 5934862.162 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553156.849 5934862.162 553151.924 5934869.374 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553151.924 5934869.374 553150.315 5934871.729 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553150.315 5934871.729 553144.36 5934880.367 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553144.36 5934880.367 553137.562 5934887.517 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553137.562 5934887.517 553136.66 5934888.715 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553136.66 5934888.715 553131.91 5934895.013 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553131.91 5934895.013 553128.423 5934900.626 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553128.423 5934900.626 553127.846 5934900.326 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553127.846 5934900.326 553127.577 5934900.81 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553127.577 5934900.81 553120.259 5934915.422 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553120.259 5934915.422 553116.322 5934922.09 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553116.322 5934922.09 553106.434 5934934.423 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553106.434 5934934.423 553107.603 5934939.345 </gml:posList>
            </gml:LineStringSegment>
          </gml:segments>
        </gml:Curve>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_4297ebe9-274d-47b9-9dcc-5336a7ac74e4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553136.66 5934851.785</gml:lowerCorner>
          <gml:upperCorner>553196.537 5934904.255</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_704e61f5-6692-4565-aeee-4dacc3271ea5" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_6889599c-38d8-4a50-b12f-b280898aef3f" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_DE21D296-7E88-4277-869B-C08189B88609" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_EC7A31DB-7E40-4C31-9175-C85183B15628" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_A860E10E-FD8F-4E56-B613-D31EBBD5CD13" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7710651A-5C95-4EC3-8A2D-D4F468DB085E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553169.372 5934893.251 553165.037 5934898.982 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5A38E4F2-9868-44ED-8B1D-E386480216A3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553165.037 5934898.982 553161.048 5934904.255 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0916D56E-94A3-4821-A135-EE42E60DBDD7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553161.048 5934904.255 553153.018 5934899.137 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_29C9F08E-5B26-476A-A3D2-A8124A260B4E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553153.018 5934899.137 553136.66 5934888.715 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_AE5003EC-182F-4D97-8345-98375B5F2FBB" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553136.66 5934888.715 553137.562 5934887.517 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0579DDFC-E83B-4C09-ADE0-F8FAB7ECFEF7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553137.562 5934887.517 553144.36 5934880.367 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_15FBB3C2-6D92-43D0-83C7-E2DA00611515" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553144.36 5934880.367 553150.315 5934871.729 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EE627B8A-B66D-4C52-B5C2-E56C78CCC2F5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553150.315 5934871.729 553151.924 5934869.374 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_F2172CE6-DFCA-47D5-B44C-2926EE89FAD4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553151.924 5934869.374 553156.849 5934862.162 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C09D03C5-62C2-4BFC-8A55-CB6C8D45B8E3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553156.849 5934862.162 553158.242 5934860.856 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_66753C09-4FA1-4276-82D2-839568F55260" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553158.242 5934860.856 553168.374 5934867.382 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2A09B16A-B3B8-4BB4-8BED-9667C91876E9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553168.374 5934867.382 553179.563 5934851.785 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_57EA437B-D412-4882-936D-C713428D65A2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553179.563 5934851.785 553181.257 5934852.962 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_ABF251F6-5BF5-4BD4-861A-1E8BC9C87FB7" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553181.257 5934852.962 553184.677 5934855.336 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_957C0228-C726-4D4F-BB26-9F94087AC1B4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553184.677 5934855.336 553191.385 5934860.375 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_16716070-B0C2-43CA-A54A-7270AC7AA96E" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553191.385 5934860.375 553193.388 5934859.253 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9F35E01C-4A57-457F-9DCF-0622A971936D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553193.388 5934859.253 553194.33 5934858.414 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4DA4EAD0-1737-4337-BDF8-3DDDDA96B212" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553194.33 5934858.414 553196.537 5934856.221 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_104E6AFE-ACB6-437C-A54C-4B97F1B69E66" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553196.537 5934856.221 553195.395 5934860.083 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_C7B1F20A-8C8D-4F48-ADBA-E17B2EB53FA6" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553195.395 5934860.083 553194.323873055 5934862.71730787 553193.227 5934865.341 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_4366E295-4954-40F7-95DE-53EAA76F7357" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553193.227 5934865.341 553192.235 5934864.566 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_68A55194-CA5C-431E-9210-2AF0423EA4C3" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553192.235 5934864.566 553190.526427202 5934865.6428734 553188.507 5934865.671 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_407A1C6F-28AB-48C0-88CC-6CA86F88A473" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553188.507 5934865.671 553186.043 5934865.076 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_49A9D003-15D7-4B2F-8835-2534027CC858" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553186.043 5934865.076 553184.207657243 5934865.39815112 553182.732 5934866.536 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2EDC62B6-12FC-4430-A035-A63567B4E74A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553182.732 5934866.536 553178.731 5934871.214 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E04C1CEE-7345-410B-B92A-02A52EFE2845" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553178.731 5934871.214 553174.627 5934878.962 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_CA22235A-10E8-4DE8-9652-1B3C885CDB0D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553174.627 5934878.962 553183.625 5934883.814 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E5755335-091D-493E-A104-5D92F8C47E94" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.625 5934883.814 553183.177 5934886.615 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8A925F50-AD2F-419D-8B03-9718997EC6C4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553183.177 5934886.615 553173.903 5934885.016 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5A187C00-CA3E-47C9-A07C-DD3E5376BA19" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553173.903 5934885.016 553169.372 5934893.251 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_704e61f5-6692-4565-aeee-4dacc3271ea5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553149.5092 5934884.91</gml:lowerCorner>
          <gml:upperCorner>553149.5092 5934884.91</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4297ebe9-274d-47b9-9dcc-5336a7ac74e4" />
      <xplan:position>
        <gml:Point gml:id="Gml_FA57CEAB-D1AD-4F0A-A286-6B77B339461B" srsName="EPSG:25832">
          <gml:pos>553149.5092 5934884.91</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_6889599c-38d8-4a50-b12f-b280898aef3f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553149.292 5934890.657</gml:lowerCorner>
          <gml:upperCorner>553149.292 5934890.657</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4297ebe9-274d-47b9-9dcc-5336a7ac74e4" />
      <xplan:position>
        <gml:Point gml:id="Gml_6ACB61D5-A4A4-4E45-AD62-16CE47487FB5" srsName="EPSG:25832">
          <gml:pos>553149.292 5934890.657</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_DE21D296-7E88-4277-869B-C08189B88609">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553152.2748 5934879.274</gml:lowerCorner>
          <gml:upperCorner>553152.2748 5934879.274</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4297ebe9-274d-47b9-9dcc-5336a7ac74e4" />
      <xplan:position>
        <gml:Point gml:id="Gml_8CBD1D19-9BA7-4BDF-A59B-056BD9CC57CD" srsName="EPSG:25832">
          <gml:pos>553152.2748 5934879.274</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_EC7A31DB-7E40-4C31-9175-C85183B15628">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553167.5148 5934873.4531</gml:lowerCorner>
          <gml:upperCorner>553167.5148 5934873.4531</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_4297ebe9-274d-47b9-9dcc-5336a7ac74e4" />
      <xplan:position>
        <gml:Point gml:id="Gml_F00CD68B-0B38-4CCD-8ABE-496A1EABAF75" srsName="EPSG:25832">
          <gml:pos>553167.5148 5934873.4531</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_3ef399b8-7c20-4036-acc4-0abe0858de7c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553177.821 5934868.482</gml:lowerCorner>
          <gml:upperCorner>553188.878 5934881.261</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_17EA12B7-3782-4B1A-8F29-0C590D59BF3D" srsName="EPSG:25832">
          <gml:posList>553177.821 5934878.096 553182.743 5934868.482 553188.878 5934871.688 
553183.876 5934881.261 553177.821 5934878.096 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_845323e0-635b-45b5-b639-257674dbb5c2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553201.747 5934907.922</gml:lowerCorner>
          <gml:upperCorner>553213.21 5934919.581</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_5b85dce4-48c9-4194-b89d-8c3bebf546e3" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_580BE6DE-1C78-4290-9C16-3839E492EFF6" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553213.21 5934912.26 553207.372 5934919.581 553201.747 5934915.215 
553207.62 5934907.922 553213.21 5934912.26 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_5b85dce4-48c9-4194-b89d-8c3bebf546e3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553207.708 5934913.346</gml:lowerCorner>
          <gml:upperCorner>553207.708 5934913.346</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_845323e0-635b-45b5-b639-257674dbb5c2" />
      <xplan:position>
        <gml:Point gml:id="Gml_0968DF04-34B1-453C-9425-1E3752F26845" srsName="EPSG:25832">
          <gml:pos>553207.708 5934913.346</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_c5fb5d35-13f7-4b3b-93fb-e0a9860c8215">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553133.461 5934912.266</gml:lowerCorner>
          <gml:upperCorner>553160.29 5934936.588</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_78ef0ebd-ea2d-441f-968f-e544d9e1c9dc" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_06c0ec67-a008-4b9a-8f55-9943b51ce168" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_EA60A4AB-F558-4E8D-B22E-DD7754BF35FF" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_A1979071-3E4C-4722-838A-0F180956F880" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_363E5DA9-6297-41F1-8DF7-FC7071C7B084" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553160.29 5934917.59 553158.107 5934919.517 553157.916 5934920.143 
553157.142 5934922.128 553156.212 5934932.322 553153.416 5934935.409 
553139.99 5934936.588 553139.56 5934929.513 553133.461 5934927.565 
553143.986 5934912.266 553145.83 5934913.553 553149.946 5934915.163 
553153.775 5934916.387 553157.67 5934916.321 553160.29 5934917.59 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_78ef0ebd-ea2d-441f-968f-e544d9e1c9dc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553146.2819 5934928.71</gml:lowerCorner>
          <gml:upperCorner>553146.2819 5934928.71</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c5fb5d35-13f7-4b3b-93fb-e0a9860c8215" />
      <xplan:position>
        <gml:Point gml:id="Gml_9FD34656-8227-496D-A99F-CF32A9B7351F" srsName="EPSG:25832">
          <gml:pos>553146.2819 5934928.71</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_06c0ec67-a008-4b9a-8f55-9943b51ce168">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.672 5934923.931</gml:lowerCorner>
          <gml:upperCorner>553143.672 5934923.931</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c5fb5d35-13f7-4b3b-93fb-e0a9860c8215" />
      <xplan:position>
        <gml:Point gml:id="Gml_6EF404D5-B8AE-4D3E-8D86-846D9A2B3BF2" srsName="EPSG:25832">
          <gml:pos>553143.672 5934923.931</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_EA60A4AB-F558-4E8D-B22E-DD7754BF35FF">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553144.7324 5934918.866</gml:lowerCorner>
          <gml:upperCorner>553144.7324 5934918.866</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c5fb5d35-13f7-4b3b-93fb-e0a9860c8215" />
      <xplan:position>
        <gml:Point gml:id="Gml_52F512BC-838C-4D59-8921-80353070B573" srsName="EPSG:25832">
          <gml:pos>553144.7324 5934918.866</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_A1979071-3E4C-4722-838A-0F180956F880">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553154.3633 5934919.6068</gml:lowerCorner>
          <gml:upperCorner>553154.3633 5934919.6068</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c5fb5d35-13f7-4b3b-93fb-e0a9860c8215" />
      <xplan:position>
        <gml:Point gml:id="Gml_2FF52E7A-BEC4-40E5-A7F2-C894359FDC85" srsName="EPSG:25832">
          <gml:pos>553154.3633 5934919.6068</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_61c03deb-c952-41a0-92ca-1aa011e4e3a6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553116.322 5934900.81</gml:lowerCorner>
          <gml:upperCorner>553143.986 5934927.565</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_61ADE8AE-E024-4601-98FC-8743BE696C3F" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_E9E5FFD4-20CE-481D-BB7A-CB1903B5F671" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_3CCFF559-C66A-409E-AE2B-574B161112BD" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_8C7580A9-4040-4C9B-A082-C75DFA457CA4" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553129.139 5934901.9 553130.58 5934902.906 553133.903 5934905.226 
553134.78 5934905.839 553143.986 5934912.266 553133.461 5934927.565 
553116.322 5934922.09 553120.259 5934915.422 553127.577 5934900.81 
553129.139 5934901.9 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>4</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_61ADE8AE-E024-4601-98FC-8743BE696C3F">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553131.3761 5934923.3818</gml:lowerCorner>
          <gml:upperCorner>553131.3761 5934923.3818</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_61c03deb-c952-41a0-92ca-1aa011e4e3a6" />
      <xplan:position>
        <gml:Point gml:id="Gml_3C11E9A7-B817-4150-8D14-A99CE5C7270C" srsName="EPSG:25832">
          <gml:pos>553131.3761 5934923.3818</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_E9E5FFD4-20CE-481D-BB7A-CB1903B5F671">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553131.2228 5934913.6716</gml:lowerCorner>
          <gml:upperCorner>553131.2228 5934913.6716</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_61c03deb-c952-41a0-92ca-1aa011e4e3a6" />
      <xplan:position>
        <gml:Point gml:id="Gml_850ECB63-3EF5-4892-AA79-6C0F15EC9EAB" srsName="EPSG:25832">
          <gml:pos>553131.2228 5934913.6716</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_3CCFF559-C66A-409E-AE2B-574B161112BD">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553129.159 5934918.6722</gml:lowerCorner>
          <gml:upperCorner>553129.159 5934918.6722</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_61c03deb-c952-41a0-92ca-1aa011e4e3a6" />
      <xplan:position>
        <gml:Point gml:id="Gml_D6686385-6BF6-4E63-9BFC-C2FFA7BFDE2B" srsName="EPSG:25832">
          <gml:pos>553129.159 5934918.6722</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_5b09fce9-7b05-498d-bdc1-6f2f78e7ef27">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553010.759 5934964.661</gml:lowerCorner>
          <gml:upperCorner>553025.969 5934979.633</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_7021F9BE-0998-48CF-8DC3-35D8909C65E7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553025.969 5934973.411 553017.028 5934979.633 553010.759 5934971.166 
553019.544 5934964.661 553025.969 5934973.411 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_de6f3e96-78df-42d5-8835-44826509ab59">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552883.311 5935021.549</gml:lowerCorner>
          <gml:upperCorner>552888.66 5935027.056</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_197BABC5-14E2-4754-AC21-AD74801F05D2" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552888.66 5935023.541 552886.373 5935027.056 552883.311 5935025.064 
552885.6 5935021.549 552888.66 5935023.541 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_e7ff3715-6e8c-4a75-833f-776331abf102">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553171.904 5934942.663</gml:lowerCorner>
          <gml:upperCorner>553185.035 5934955.248</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_bfb48166-05bc-48af-9994-11ef26020460" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_53A10A1C-FD47-430D-843E-400C0379F82D" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553179.39 5934955.248 553177.876 5934954.81 553171.904 5934950.732 
553177.07 5934942.663 553177.7 5934943.066 553184.404 5934947.354 
553182.789 5934949.876 553185.035 5934951.313 553182.869 5934954.699 
553180.638 5934953.273 553179.39 5934955.248 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_bfb48166-05bc-48af-9994-11ef26020460">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553178.722 5934949.423</gml:lowerCorner>
          <gml:upperCorner>553178.722 5934949.423</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e7ff3715-6e8c-4a75-833f-776331abf102" />
      <xplan:position>
        <gml:Point gml:id="Gml_D12C4AA7-0DF3-4554-B91E-4652907994F8" srsName="EPSG:25832">
          <gml:pos>553178.722 5934949.423</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_1c4745ae-5174-4584-8088-7e86856d290b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553127.577 5934885.016</gml:lowerCorner>
          <gml:upperCorner>553183.177 5934917.59</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_8e9e567b-ebb5-46d7-b646-2ebb4b511ac4" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_f2f1fd49-5c43-4e50-b477-8fe72f7ee72a" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_4F0AB60A-5562-4D3C-99B6-4770305A1FF3" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_1111027D-1EF7-48EA-AAC0-7000B5D50A23" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0DA0AF6E-72C4-4AAF-942D-D456C14D1018" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5A7D8C85-B0F7-4D71-8657-8D2BB2E4CC1A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553160.29 5934917.59 553157.67 5934916.321 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_82C2D477-6E9E-4790-AFCF-473B9B39942B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553157.67 5934916.321 553153.775 5934916.387 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_51618E46-4962-42A5-964F-DA5AD5C613B1" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553153.775 5934916.387 553149.946 5934915.163 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_EEC5D57D-AD8D-41AF-889C-FF85932C7BCF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553149.946 5934915.163 553145.83 5934913.553 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3FEFD406-3CA8-42E7-9E59-C00110B1AC60" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553145.83 5934913.553 553143.986 5934912.266 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8E8CFE72-495D-4AC1-8676-61FA6EFB6C6A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553143.986 5934912.266 553134.78 5934905.839 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_DCB90262-2504-4957-A174-3756AECD6D97" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553134.78 5934905.839 553133.903 5934905.226 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_A55D0D7F-CA46-40C9-B064-CB6E239B15E6" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553133.903 5934905.226 553130.58 5934902.906 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7F45DF2E-A0F5-4438-8BCF-2EF3E7DD740F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553130.58 5934902.906 553129.139 5934901.9 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0C2D7348-7D55-4909-AAE5-A782CD970466" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553129.139 5934901.9 553127.577 5934900.81 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_1B79BC6F-4371-47CF-9759-710038A59FA0" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553127.577 5934900.81 553127.846 5934900.326 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_461B2E22-64A1-4CEB-9830-9697E0AF720A" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553127.846 5934900.326 553128.423 5934900.626 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B70CAEB0-C64E-4398-8448-EDF3B446DB70" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553128.423 5934900.626 553131.91 5934895.013 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_73FDF476-AFCF-4E2D-903E-0BCBF98B9B18" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553131.91 5934895.013 553136.66 5934888.715 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_9FA31431-9E51-4C44-B1FE-BFBA5FF5DA81" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553136.66 5934888.715 553153.018 5934899.137 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_FE335FCE-CF71-45AF-AF16-8982C45038D6" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553153.018 5934899.137 553161.048 5934904.255 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_7EF5AE08-D03C-47DA-8E13-A720B4482F57" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553161.048 5934904.255 553165.037 5934898.982 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_35C16B8F-9F39-40EF-8E94-71D70CF0C649" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553165.037 5934898.982 553169.372 5934893.251 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C39B5133-1C57-426F-BB67-980715A54134" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553169.372 5934893.251 553173.903 5934885.016 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0C660865-AC4D-490B-9EA7-43782F4743CA" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553173.903 5934885.016 553183.177 5934886.615 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_9F1DCA1D-AC58-4440-B1F7-77F829D417A6" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553183.177 5934886.615 553181.305105664 5934893.70674587 553177.597 5934900.035 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E655C4A1-AA5F-4DB3-AC94-53E0A1DAB1E9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553177.597 5934900.035 553175.612 5934902.116 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_050FE005-F0FF-493B-8107-92D91103B2B4" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553175.612 5934902.116 553168.888 5934909.251 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2CA9CCAA-D062-4147-8C25-6B3B4A79163D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553168.888 5934909.251 553160.29 5934917.59 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_8e9e567b-ebb5-46d7-b646-2ebb4b511ac4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553168.7718 5934900.6501</gml:lowerCorner>
          <gml:upperCorner>553168.7718 5934900.6501</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1c4745ae-5174-4584-8088-7e86856d290b" />
      <xplan:position>
        <gml:Point gml:id="Gml_E06D7AD0-9148-493A-B4B9-74E022BF7BE2" srsName="EPSG:25832">
          <gml:pos>553168.7718 5934900.6501</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_f2f1fd49-5c43-4e50-b477-8fe72f7ee72a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553166.124 5934905.603</gml:lowerCorner>
          <gml:upperCorner>553166.124 5934905.603</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1c4745ae-5174-4584-8088-7e86856d290b" />
      <xplan:position>
        <gml:Point gml:id="Gml_0512FA87-4143-4992-93C0-15AB0FF3D0DD" srsName="EPSG:25832">
          <gml:pos>553166.124 5934905.603</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_4F0AB60A-5562-4D3C-99B6-4770305A1FF3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553146.304 5934908.5123</gml:lowerCorner>
          <gml:upperCorner>553146.304 5934908.5123</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1c4745ae-5174-4584-8088-7e86856d290b" />
      <xplan:position>
        <gml:Point gml:id="Gml_39662378-9E7B-4ED3-B3CF-B0AF699F2C32" srsName="EPSG:25832">
          <gml:pos>553146.304 5934908.5123</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_1111027D-1EF7-48EA-AAC0-7000B5D50A23">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553150.1934 5934902.1622</gml:lowerCorner>
          <gml:upperCorner>553150.1934 5934902.1622</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1c4745ae-5174-4584-8088-7e86856d290b" />
      <xplan:position>
        <gml:Point gml:id="Gml_82AD0B52-2844-457B-A6DF-7363D0F04189" srsName="EPSG:25832">
          <gml:pos>553150.1934 5934902.1622</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_90193278-e141-437b-9c52-d5678e60a315">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553276.21 5934954.073</gml:lowerCorner>
          <gml:upperCorner>553294.915 5934971.548</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_eddcc363-94c3-41ce-a417-7796813fa692" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_290FE48E-1E16-4CC0-919C-00A297795E20" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553294.915 5934967.306 553289.215 5934971.548 553282.821 5934962.956 
553281.621 5934963.851 553276.21 5934956.585 553279.581 5934954.073 
553284.996 5934961.34 553288.519 5934958.716 553294.915 5934967.306 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_eddcc363-94c3-41ce-a417-7796813fa692">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553287.6214 5934962.2994</gml:lowerCorner>
          <gml:upperCorner>553287.6214 5934962.2994</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_90193278-e141-437b-9c52-d5678e60a315" />
      <xplan:position>
        <gml:Point gml:id="Gml_BEAE3AC9-4A75-44D1-B324-B498AF7BA478" srsName="EPSG:25832">
          <gml:pos>553287.6214 5934962.2994</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_3b3ef4de-276a-48af-a120-f281a5552e17">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553170.395 5934855.647</gml:lowerCorner>
          <gml:upperCorner>553182.888 5934869.329</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_23D4C87A-8250-4250-A90D-6109177C5D59" srsName="EPSG:25832">
          <gml:posList>553182.888 5934859.703 553175.987 5934869.329 553170.395 5934865.32 
553177.311 5934855.647 553182.888 5934859.703 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_0aab0715-b872-4b50-a0b6-8aeebf51ca56">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553097.338 5934901.818</gml:lowerCorner>
          <gml:upperCorner>553109.02 5934913.998</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_405c6016-bdb8-45d5-a0fd-a1795f9b1dac" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C6AC4E0A-4AA4-4574-A8AA-BED2F35AF035" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553109.02 5934910.398 553101.386 5934913.998 553097.338 5934905.415 
553101.359 5934903.52 553104.97 5934901.818 553109.02 5934910.398 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_405c6016-bdb8-45d5-a0fd-a1795f9b1dac">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553105.078 5934911.068</gml:lowerCorner>
          <gml:upperCorner>553105.078 5934911.068</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0aab0715-b872-4b50-a0b6-8aeebf51ca56" />
      <xplan:position>
        <gml:Point gml:id="Gml_87BE419B-9616-4546-BB3D-51886443CD4A" srsName="EPSG:25832">
          <gml:pos>553105.078 5934911.068</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_03850a46-1088-4bf8-a11f-547e17ea7052">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553018.342 5934968.139</gml:lowerCorner>
          <gml:upperCorner>553091.729 5935034.119</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_9E764B48-EF89-4E30-8565-D30FB93A33A0" srsName="EPSG:25832">
          <gml:posList>553018.342 5934991.971 553050.42 5934968.139 553069.378 5934981.975 
553082.284 5934991.392 553091.729 5934992.677 553081.048 5935001.594 
553048.283 5935034.119 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_193a5e28-7b26-4d97-920b-b136f6ad59c2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553136.66 5934888.715</gml:lowerCorner>
          <gml:upperCorner>553161.048 5934904.255</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_CEAD6BF8-C7FF-416B-869A-E5EFFE0F3EAA" srsName="EPSG:25832">
          <gml:posList>553136.66 5934888.715 553153.018 5934899.137 553161.048 5934904.255 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_a0709384-8458-4eb1-8d98-75e22687cc9c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553193.008 5934917.15</gml:lowerCorner>
          <gml:upperCorner>553204.267 5934928.503</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_a59ef4b5-d171-4862-a8ff-cd07d348d33d" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_5E72416A-1B79-4F0B-82D4-9483DAF6EEA5" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553204.267 5934921.795 553198.695 5934928.503 553193.008 5934923.951 
553198.675 5934917.15 553204.267 5934921.795 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_a59ef4b5-d171-4862-a8ff-cd07d348d33d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553198.927 5934922.542</gml:lowerCorner>
          <gml:upperCorner>553198.927 5934922.542</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_a0709384-8458-4eb1-8d98-75e22687cc9c" />
      <xplan:position>
        <gml:Point gml:id="Gml_7C03E06F-0978-4DE8-9582-1951D37AE21B" srsName="EPSG:25832">
          <gml:pos>553198.927 5934922.542</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_eaccfc25-af38-4ef2-aedf-84da7af2b00c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.297 5935060.673</gml:lowerCorner>
          <gml:upperCorner>553160.168 5935067.994</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_86539EBC-B607-417D-977A-51C984490708" srsName="EPSG:25832">
          <gml:posList>553160.168 5935060.673 553159.297 5935067.994 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_0f5d3c76-97ab-4d95-afa4-90f8a621a166">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553130.869 5934818.851</gml:lowerCorner>
          <gml:upperCorner>553150.063 5934843.455</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_5f6fd4af-6bb1-438c-b91e-e3e244a35fb6" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_88FD9839-2911-482E-804C-3353747FC1FD" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553150.063 5934827.593 553135.724 5934843.455 553130.869 5934839.065 
553138.851 5934830.242 553134.044 5934825.888 553140.416 5934818.851 
553150.063 5934827.593 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_5f6fd4af-6bb1-438c-b91e-e3e244a35fb6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553142.055 5934826.741</gml:lowerCorner>
          <gml:upperCorner>553142.055 5934826.741</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0f5d3c76-97ab-4d95-afa4-90f8a621a166" />
      <xplan:position>
        <gml:Point gml:id="Gml_37ED4DCB-D654-4CCD-82D0-FE20A7DF587F" srsName="EPSG:25832">
          <gml:pos>553142.055 5934826.741</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_640786c9-4bbb-409f-a90a-7a53df0b97ec">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.286 5934964.625</gml:lowerCorner>
          <gml:upperCorner>553166.48 5934972.966</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_B1CC52D3-EABB-45C6-A611-3B0B2B1BD483" srsName="EPSG:25832">
          <gml:posList>553166.48 5934972.331 553160.011 5934972.966 553159.286 5934965.585 
553165.723 5934964.625 553166.48 5934972.331 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_ac5e87f5-2c3e-4c55-a691-3133f8f95c23">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553112.018 5934886.122</gml:lowerCorner>
          <gml:upperCorner>553126.475 5934900.023</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_80B7338E-A64A-4B60-96E0-25CF4D732032" srsName="EPSG:25832">
          <gml:posList>553126.475 5934892.462 553124.377 5934895.368 553121.005 5934900.023 
553112.018 5934893.514 553117.358 5934886.122 553126.475 5934892.462 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_7f1642c7-8b93-44f3-bd0c-b4ed3ef11375">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553215.029 5934917.011</gml:lowerCorner>
          <gml:upperCorner>553289.676 5934997.286</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_43afaccc-b92e-40c8-9298-9fe813d17625" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_806b3e8f-060f-4895-95ad-29339633d780" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_48fbfcdb-21d8-4579-a616-ea3166363bd1" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_B82BA443-3CA8-4E44-8681-72894091ABB4" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553277.975 5934949.65 553271.471 5934954.505 553289.676 5934978.967 
553261.183 5934997.286 553253.356 5934985.837 553215.029 5934933.212 
553227.184 5934925.38 553228.929 5934924.441 553229.109 5934924.689 
553240.721 5934917.011 553241.738 5934918.349 553251.227 5934930.652 
553259.312 5934940.966 553262.451 5934939.088 553277.975 5934949.65 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_43afaccc-b92e-40c8-9298-9fe813d17625">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553263.051 5934980.817</gml:lowerCorner>
          <gml:upperCorner>553263.051 5934980.817</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7f1642c7-8b93-44f3-bd0c-b4ed3ef11375" />
      <xplan:position>
        <gml:Point gml:id="Gml_3464F6AE-236E-4559-A2A7-A20ED8AEC2F9" srsName="EPSG:25832">
          <gml:pos>553263.051 5934980.817</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_806b3e8f-060f-4895-95ad-29339633d780">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553257.103 5934975.599</gml:lowerCorner>
          <gml:upperCorner>553257.103 5934975.599</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7f1642c7-8b93-44f3-bd0c-b4ed3ef11375" />
      <xplan:position>
        <gml:Point gml:id="Gml_D50E3482-4BE4-41B3-8E06-35DD72A3356D" srsName="EPSG:25832">
          <gml:pos>553257.103 5934975.599</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_48fbfcdb-21d8-4579-a616-ea3166363bd1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553269.716 5934980.855</gml:lowerCorner>
          <gml:upperCorner>553269.716 5934980.855</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_7f1642c7-8b93-44f3-bd0c-b4ed3ef11375" />
      <xplan:position>
        <gml:Point gml:id="Gml_39E0C50C-A102-4306-AF65-F8DF45858344" srsName="EPSG:25832">
          <gml:pos>553269.716 5934980.855</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_f48acf6e-5290-413f-b15f-a25ee0582b83">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.972 5934853.676</gml:lowerCorner>
          <gml:upperCorner>553149.943 5934868.755</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_C8562392-AD82-4929-B778-0886B52D21ED" srsName="EPSG:25832">
          <gml:posList>553102.972 5934853.676 553109.541 5934857.883 553115.221 5934861.52 
553126.516 5934868.755 553128.511 5934865.976 553135.335 5934856.473 
553149.943 5934865.779 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_5d9af267-684b-4b16-9c06-56d952169f62">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553121.255 5935015.808</gml:lowerCorner>
          <gml:upperCorner>553151.158 5935074.21</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_eb8103b3-4021-4c87-aa5f-440c8663569b" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_c7ddcd14-32cd-4d5b-bca3-43cd64651433" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_d1ab6b20-cef6-4e3e-ad1d-c732e2d71680" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_8EA078BB-3149-47E7-A487-87B982B43506" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_45940F8C-EC1F-491C-8BE2-331F0619142A" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_77E42043-22A9-49A9-917D-025297426B46" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553150.719 5935024.482 553151.158 5935029.663 553147.609 5935029.715 
553142.762 5935074.21 553123.501 5935072.151 553121.255 5935067.968 
553121.856 5935058.74 553121.441 5935055.683 553137.515 5935025.234 
553143.91 5935015.808 553150.719 5935024.482 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_eb8103b3-4021-4c87-aa5f-440c8663569b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553132.563 5935047.239</gml:lowerCorner>
          <gml:upperCorner>553132.563 5935047.239</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5d9af267-684b-4b16-9c06-56d952169f62" />
      <xplan:position>
        <gml:Point gml:id="Gml_692B2FDD-156F-462D-B59D-A9E1FF2E731A" srsName="EPSG:25832">
          <gml:pos>553132.563 5935047.239</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_c7ddcd14-32cd-4d5b-bca3-43cd64651433">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.696 5935041.596</gml:lowerCorner>
          <gml:upperCorner>553143.696 5935041.596</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5d9af267-684b-4b16-9c06-56d952169f62" />
      <xplan:position>
        <gml:Point gml:id="Gml_7763D9BC-BDEF-4162-A5C6-3A4986AA49C9" srsName="EPSG:25832">
          <gml:pos>553143.696 5935041.596</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_d1ab6b20-cef6-4e3e-ad1d-c732e2d71680">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553137.095 5935041.596</gml:lowerCorner>
          <gml:upperCorner>553137.095 5935041.596</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5d9af267-684b-4b16-9c06-56d952169f62" />
      <xplan:position>
        <gml:Point gml:id="Gml_71F9282F-4F37-4A3E-9100-472E17822589" srsName="EPSG:25832">
          <gml:pos>553137.095 5935041.596</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_8EA078BB-3149-47E7-A487-87B982B43506">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553140.1857 5935034.6727</gml:lowerCorner>
          <gml:upperCorner>553140.1857 5935034.6727</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5d9af267-684b-4b16-9c06-56d952169f62" />
      <xplan:position>
        <gml:Point gml:id="Gml_29DF707D-8D83-402A-A1B6-910F51020CB3" srsName="EPSG:25832">
          <gml:pos>553140.1857 5935034.6727</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_45940F8C-EC1F-491C-8BE2-331F0619142A">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.493 5935026.7352</gml:lowerCorner>
          <gml:upperCorner>553143.493 5935026.7352</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5d9af267-684b-4b16-9c06-56d952169f62" />
      <xplan:position>
        <gml:Point gml:id="Gml_93CB809E-3F0B-4D4F-94F8-6650824EF4D7" srsName="EPSG:25832">
          <gml:pos>553143.493 5935026.7352</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_2fc4caa5-7132-42a8-abd7-70db13017105">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553158.242 5934851.785</gml:lowerCorner>
          <gml:upperCorner>553196.537 5934867.382</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_04BEA13D-EFFE-4EEA-A704-C141095FDF7D" srsName="EPSG:25832">
          <gml:posList>553158.242 5934860.856 553168.374 5934867.382 553179.563 5934851.785 
553181.257 5934852.962 553184.677 5934855.336 553191.385 5934860.375 
553193.388 5934859.253 553194.33 5934858.414 553196.537 5934856.221 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_5c642674-0122-497a-b4c6-d489e2914e70">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553078.285 5934878.977</gml:lowerCorner>
          <gml:upperCorner>553096.924 5934896.586</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0a3de316-3ef3-438c-a34d-52fab492d2ad" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_D95D4A5F-FAB3-4B97-B01A-1CD1510EE90F" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553096.924 5934887.963 553090.645 5934896.586 553078.285 5934887.597 
553084.553 5934878.977 553096.924 5934887.963 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0a3de316-3ef3-438c-a34d-52fab492d2ad">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553087.602 5934887.781</gml:lowerCorner>
          <gml:upperCorner>553087.602 5934887.781</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5c642674-0122-497a-b4c6-d489e2914e70" />
      <xplan:position>
        <gml:Point gml:id="Gml_69FA9BF4-4799-4DD4-910C-59EFBCFCE999" srsName="EPSG:25832">
          <gml:pos>553087.602 5934887.781</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_cdac95e0-2eda-4661-9806-58fc8b53257b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553158.234 5934890.502</gml:lowerCorner>
          <gml:upperCorner>553297.422 5934979.522</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_C85737FF-9808-4828-A70C-4DA243FB049D" srsName="EPSG:25832">
          <gml:posList>553297.422 5934963.64 553296.465 5934964.008 553281.477 5934952.138 
553277.975 5934949.65 553262.451 5934939.088 553259.312 5934940.966 
553251.227 5934930.652 553241.738 5934918.349 553240.721 5934917.011 
553232.334 5934905.994 553229.571 5934901.985 553225.945 5934895.043 
553225.005 5934893.858 553223.027 5934892.152 553221.753 5934891.379 
553220.926 5934890.99 553219.476 5934890.661 553217.807 5934890.502 
553216.457 5934890.731 553215.367 5934891.142 553213.947 5934892.182 
553212.496 5934893.811 553210.766 5934896.192 553206.243 5934901.709 
553197.458 5934912.244 553197.366 5934912.538 553197.095 5934912.938 
553196.604 5934912.688 553187.964 5934921.061 553182.931 5934925.566 
553178.548 5934930.234 553176.754 5934932.327 553175.469 5934933.103 
553171.528 5934939.116 553166.521 5934947.057 553161.764 5934955.9 
553161.945 5934958.374 553158.234 5934965.678 553158.834 5934975.654 
553159.066 5934979.522 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_fdd9d345-e676-4da0-840b-3c965f3f32d6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553025.713 5934982.262</gml:lowerCorner>
          <gml:upperCorner>553047.71 5935006.263</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_F201C107-ED3E-4D50-BE40-1C99D14FE25A" srsName="EPSG:25832">
          <gml:posList>553047.71 5934997.886 553037.468 5935006.263 553025.713 5934991.709 
553037.526 5934982.262 553041.209 5934987.02 553039.781 5934988.191 
553047.71 5934997.886 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_4af95b70-4a69-4fc4-a550-e6d3bb40743c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552949.495 5934955.361</gml:lowerCorner>
          <gml:upperCorner>552974.219 5934968.897</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_3E5807AC-E2DE-4600-A7C9-8F92F8ABA6A7" srsName="EPSG:25832">
          <gml:posList>552974.219 5934960.018 552952.039 5934968.897 552949.495 5934965.341 
552955.243 5934962.569 552972.213 5934955.361 552972.354 5934955.689 
552974.219 5934960.018 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_70ed3a05-95a8-4655-b881-9ea81c820448">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553039.175 5935001.631</gml:lowerCorner>
          <gml:upperCorner>553058.315 5935024.795</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_68D2329F-9A12-4352-8FB6-1F01D558F4DF" srsName="EPSG:25832">
          <gml:posList>553057.133 5935009.642 553053.456 5935012.81 553058.315 5935018.442 
553050.948 5935024.795 553039.175 5935011.143 553050.225 5935001.631 
553057.133 5935009.642 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_ecf2f3c5-dbb4-4133-84de-ffd946251b8c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.894 5934869.626</gml:lowerCorner>
          <gml:upperCorner>553211.811 5934934.153</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Curve gml:id="Gml_711A9A0A-85C3-4FB2-BEF5-AFF1C3D6A755" srsName="EPSG:25832">
          <gml:segments>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553160.51 5934932.7 553162.208 5934934.153 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553162.208 5934934.153 553164.759 5934933.554 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553164.759 5934933.554 553167.982 5934931.983 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553167.982 5934931.983 553172.659 5934928.499 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553172.659 5934928.499 553173.742 5934927.691 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553173.742 5934927.691 553176.762 5934924.672 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553176.762 5934924.672 553180.645 5934920.789 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553180.645 5934920.789 553181.662 5934919.772 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553181.662 5934919.772 553192.546 5934908.48 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553192.546 5934908.48 553192.797 5934908.601 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553192.797 5934908.601 553199.046989233 5934901.73301248 553204.931 5934894.549 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553204.931 5934894.549 553211.811 5934884.804 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553211.811 5934884.804 553203.957362031 5934878.60729685 553196.342 5934872.12 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553196.342 5934872.12 553194.051 5934869.626 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553194.051 5934869.626 553192.234 5934874.11 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553192.234 5934874.11 553187.582 5934883.144 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553187.582 5934883.144 553185.877 5934886.896 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553185.877 5934886.896 553183.41 5934896.056 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553183.41 5934896.056 553179.316 5934902.227 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553179.316 5934902.227 553171.7 5934910.53 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553171.7 5934910.53 553171.133 5934911.148 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553171.133 5934911.148 553167.986 5934914.118 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553167.986 5934914.118 553164.182 5934917.711 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553164.182 5934917.711 553160.908 5934920.802 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553160.908 5934920.802 553160.454 5934921.468 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553160.454 5934921.468 553159.894 5934928.518 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553159.894 5934928.518 553160.51 5934932.7 </gml:posList>
            </gml:LineStringSegment>
          </gml:segments>
        </gml:Curve>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_63205c3a-e599-4552-8c80-6f7af8c47c62">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553049.399 5934915.189</gml:lowerCorner>
          <gml:upperCorner>553065.837 5934932.267</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_28129CB8-DD68-4406-AD75-38FA06C4342C" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553065.837 5934927.293 553060.333 5934932.267 553049.399 5934920.155 
553054.904 5934915.189 553065.837 5934927.293 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_be7ed20c-dfc1-460c-8c79-c7f5b3541a92">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553039.175 5935001.631</gml:lowerCorner>
          <gml:upperCorner>553058.315 5935024.795</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_b88a5e5e-703d-4ec2-ba0a-ee854f9f59da" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C917B054-74F5-40C8-B4CF-A20AB5B839E7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553057.133 5935009.642 553053.456 5935012.81 553058.315 5935018.442 
553050.948 5935024.795 553039.175 5935011.143 553050.225 5935001.631 
553057.133 5935009.642 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_b88a5e5e-703d-4ec2-ba0a-ee854f9f59da">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553048.767 5935011.12</gml:lowerCorner>
          <gml:upperCorner>553048.767 5935011.12</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_be7ed20c-dfc1-460c-8c79-c7f5b3541a92" />
      <xplan:position>
        <gml:Point gml:id="Gml_E5B76610-E537-43C1-83D1-49C8D2EC8DD0" srsName="EPSG:25832">
          <gml:pos>553048.767 5935011.12</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.403 5934904.527</gml:lowerCorner>
          <gml:upperCorner>553124.216 5934933.542</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ae2b412b-6c1f-4170-858c-be67567b2044" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_7a0d3830-06e5-4eef-a1a2-da0878e7cd43" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_eb7ee37f-4fbe-4663-944d-b0aa1cb91d77" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_EE6F6638-280B-4DD9-A134-09F202E6A02C" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_95EF6423-F7A2-467A-AD0F-A589ECC07F10" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_7C70019A-C5C8-4115-96C0-2237E5DCD70C" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553123.543 5934906.318 553122.71 5934908.025 553115.523 5934920.749 
553105.81 5934933.542 553104.209 5934924.78 553103.961 5934923.937 
553102.403 5934918.623 553110.629 5934914.538 553114.142 5934913.007 
553115.871 5934912.111 553124.216 5934904.527 553123.543 5934906.318 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.5</xplan:GRZ>
      <xplan:Z>1</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ae2b412b-6c1f-4170-858c-be67567b2044">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553106.662 5934918.768</gml:lowerCorner>
          <gml:upperCorner>553106.662 5934918.768</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230" />
      <xplan:position>
        <gml:Point gml:id="Gml_B3221998-3838-42AA-8102-C8A2051935A9" srsName="EPSG:25832">
          <gml:pos>553106.662 5934918.768</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_7a0d3830-06e5-4eef-a1a2-da0878e7cd43">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553107.314 5934925.18</gml:lowerCorner>
          <gml:upperCorner>553107.314 5934925.18</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230" />
      <xplan:position>
        <gml:Point gml:id="Gml_E159DD89-885E-453F-B034-8A78FA37E23E" srsName="EPSG:25832">
          <gml:pos>553107.314 5934925.18</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_eb7ee37f-4fbe-4663-944d-b0aa1cb91d77">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553110.1785 5934925.1712</gml:lowerCorner>
          <gml:upperCorner>553110.1785 5934925.1712</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230" />
      <xplan:position>
        <gml:Point gml:id="Gml_A2A8942F-2618-44B5-9C5E-D9D81EAD6AA7" srsName="EPSG:25832">
          <gml:pos>553110.1785 5934925.1712</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_EE6F6638-280B-4DD9-A134-09F202E6A02C">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553107.2025 5934928.0556</gml:lowerCorner>
          <gml:upperCorner>553107.2025 5934928.0556</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230" />
      <xplan:position>
        <gml:Point gml:id="Gml_436CBF69-9DF0-4024-8788-65866A3B16BD" srsName="EPSG:25832">
          <gml:pos>553107.2025 5934928.0556</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_95EF6423-F7A2-467A-AD0F-A589ECC07F10">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553107.0226 5934921.7797</gml:lowerCorner>
          <gml:upperCorner>553107.0226 5934921.7797</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ceaf6d4-d22c-47c9-a86c-89b4dcc07230" />
      <xplan:position>
        <gml:Point gml:id="Gml_16DE3573-A74C-4AC5-9CC4-F22328157A70" srsName="EPSG:25832">
          <gml:pos>553107.0226 5934921.7797</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_a2abc023-c9ee-4ac7-878d-2e87d53f2fd5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553089.977 5934949.588</gml:lowerCorner>
          <gml:upperCorner>553105.816 5934962.49</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_CD02A742-FE2D-4EEE-B916-941414D4758A" srsName="EPSG:25832">
          <gml:posList>553103.453 5934949.588 553105.816 5934961.159 553090.458 5934962.49 
553089.977 5934951.036 553103.453 5934949.588 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_410ce780-790a-44d3-859d-de63c4cc4f83">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552938.442 5934941.015</gml:lowerCorner>
          <gml:upperCorner>552975.051 5934970.191</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_b94ac3dc-3362-4e87-9e00-477cc5718c24" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_109b304e-43aa-45ef-bf0b-e1fa61a268c3" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_60923779-3cd5-4725-80f3-ca233d9e542d" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_0FCBFA6A-6221-4E02-9D06-D412D8596D22" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_9CBB84DA-1CED-4BDA-8287-4C25023D3B20" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_AE877EF4-C677-4DC2-B518-05CA14B34FD2" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552972.213 5934955.361 552972.354 5934955.689 552974.219 5934960.018 
552974.704 5934961.145 552975.051 5934961.949 552972.303 5934962.412 
552959.196 5934967.009 552952.682 5934970.191 552943.139 5934960.325 
552943.061 5934960.243 552939.785 5934956.809 552938.442 5934955.399 
552966.031 5934941.015 552972.213 5934955.361 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>4</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_b94ac3dc-3362-4e87-9e00-477cc5718c24">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552958.938 5934962.266</gml:lowerCorner>
          <gml:upperCorner>552958.938 5934962.266</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_410ce780-790a-44d3-859d-de63c4cc4f83" />
      <xplan:position>
        <gml:Point gml:id="Gml_5B1D9BD6-FF03-4E1E-BCF0-7D40A374EA89" srsName="EPSG:25832">
          <gml:pos>552958.938 5934962.266</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_109b304e-43aa-45ef-bf0b-e1fa61a268c3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552957.655 5934952.083</gml:lowerCorner>
          <gml:upperCorner>552957.655 5934952.083</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_410ce780-790a-44d3-859d-de63c4cc4f83" />
      <xplan:position>
        <gml:Point gml:id="Gml_4D022983-60DF-43D0-9D21-66B8021CEA23" srsName="EPSG:25832">
          <gml:pos>552957.655 5934952.083</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_60923779-3cd5-4725-80f3-ca233d9e542d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552952.679 5934962.215</gml:lowerCorner>
          <gml:upperCorner>552952.679 5934962.215</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_410ce780-790a-44d3-859d-de63c4cc4f83" />
      <xplan:position>
        <gml:Point gml:id="Gml_70AA8DDA-905A-41DC-984A-675ECC545F05" srsName="EPSG:25832">
          <gml:pos>552952.679 5934962.215</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_0FCBFA6A-6221-4E02-9D06-D412D8596D22">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552953.3603 5934957.1032</gml:lowerCorner>
          <gml:upperCorner>552953.3603 5934957.1032</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_410ce780-790a-44d3-859d-de63c4cc4f83" />
      <xplan:position>
        <gml:Point gml:id="Gml_B49394F3-169D-4CCC-921D-1A321A89BC55" srsName="EPSG:25832">
          <gml:pos>552953.3603 5934957.1032</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_9CBB84DA-1CED-4BDA-8287-4C25023D3B20">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552963.8378 5934946.6257</gml:lowerCorner>
          <gml:upperCorner>552963.8378 5934946.6257</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_410ce780-790a-44d3-859d-de63c4cc4f83" />
      <xplan:position>
        <gml:Point gml:id="Gml_3D47A9CD-D280-4D60-8156-7357F0C58D56" srsName="EPSG:25832">
          <gml:pos>552963.8378 5934946.6257</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_dbd0d811-79a1-4415-9aa0-7f0648a22c25">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553025.567 5934935.381</gml:lowerCorner>
          <gml:upperCorner>553038.441 5934946.009</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_E046B7CF-4E86-42C7-9FEF-BC3EEC653F78" srsName="EPSG:25832">
          <gml:posList>553037.072 5934942.948 553028.169 5934946.009 553025.567 5934938.444 
553034.473 5934935.381 553035.377 5934938.013 553037.394 5934937.319 
553038.441 5934940.361 553036.422 5934941.056 553037.072 5934942.948 
553036.422 5934941.056 553037.072 5934942.948 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_9c9b2041-62ee-49f4-9d24-34c5312c801a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553071.815 5935035.599</gml:lowerCorner>
          <gml:upperCorner>553088.578 5935053.445</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_d536e6c1-b5f9-4bef-acde-574bb56b9d30" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_E1998BF1-726D-47E1-B5B5-4B4B5BDF7ECA" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553088.578 5935041.839 553085.173 5935045.849 553086.338 5935046.836 
553080.725 5935053.445 553071.815 5935045.881 553080.537 5935035.599 
553088.578 5935041.839 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_d536e6c1-b5f9-4bef-acde-574bb56b9d30">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553080.122 5935043.945</gml:lowerCorner>
          <gml:upperCorner>553080.122 5935043.945</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_9c9b2041-62ee-49f4-9d24-34c5312c801a" />
      <xplan:position>
        <gml:Point gml:id="Gml_2EA4F5C0-07E2-48A5-8F2A-25398F8C582B" srsName="EPSG:25832">
          <gml:pos>553080.122 5935043.945</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_0ce52e49-e37d-4d10-88cc-f648a7be5d80">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553091.292 5934946.829</gml:lowerCorner>
          <gml:upperCorner>553109.069 5934982.29</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_9820EF5F-AA85-43E1-BE17-16C62CAE75D7" srsName="EPSG:25832">
          <gml:posList>553091.292 5934982.29 553096.432 5934980.782 553109.069 5934977.075 
553107.236 5934968.106 553106.838 5934966.16 553105.816 5934961.159 
553103.453 5934949.588 553102.888 5934946.829 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_a2bfce95-4dc2-43b7-9837-c445164c1b24">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553187.372 5934942.367</gml:lowerCorner>
          <gml:upperCorner>553193.857 5934951.189</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_500e5000-78ec-490a-80ce-e3b45c54c319" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C6DF142C-E18B-4B12-B5D2-7727F9363FEB" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553193.857 5934945.025 553189.882 5934951.189 553187.507 5934949.495 
553189.045 5934947.11 553187.372 5934946.032 553189.737 5934942.367 
553193.857 5934945.025 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_500e5000-78ec-490a-80ce-e3b45c54c319">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553190.805 5934945.725</gml:lowerCorner>
          <gml:upperCorner>553190.805 5934945.725</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_a2bfce95-4dc2-43b7-9837-c445164c1b24" />
      <xplan:position>
        <gml:Point gml:id="Gml_D322115A-3488-49D1-BB7A-90B8CA28EC48" srsName="EPSG:25832">
          <gml:pos>553190.805 5934945.725</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_11d36424-1a5a-4b26-b423-2c81266be12d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553159.066 5934978.376</gml:lowerCorner>
          <gml:upperCorner>553172.55 5935014.151</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_10F56ED7-21D3-4DCD-A06A-F79373F1AAEF" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553172.55 5935012.943 553164.132 5935014.151 553159.066 5934979.522 
553167.589 5934978.376 553172.55 5935012.943 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_0d495f73-6ce9-4022-83e8-943fa77eb4bc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553184.268 5934889.037</gml:lowerCorner>
          <gml:upperCorner>553194.849 5934899.791</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_28557BB7-145E-44DC-9492-9C322C4B5217" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553194.849 5934893.378 553190.081 5934899.791 553184.268 5934895.468 
553189.051 5934889.037 553194.849 5934893.378 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_15c40f54-f48f-47d3-a71b-4ee37ad3b370">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553113.403 5934980.741</gml:lowerCorner>
          <gml:upperCorner>553126.681 5934996.76</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_CCEB0791-1621-4B0E-9AEC-662829E3759B" srsName="EPSG:25832">
          <gml:posList>553126.681 5934993.129 553118.301 5934996.76 553113.403 5934984.136 
553121.316 5934980.741 553126.681 5934993.129 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_58ea50f9-4f0a-4383-9d1d-3260c9fca761">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553174.627 5934864.566</gml:lowerCorner>
          <gml:upperCorner>553193.227 5934883.814</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Curve gml:id="Gml_6AA0D959-3DA4-4439-9500-42C85AEF30A2" srsName="EPSG:25832">
          <gml:segments>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553193.227 5934865.341 553192.235 5934864.566 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553192.235 5934864.566 553190.526427202 5934865.6428734 553188.507 5934865.671 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553188.507 5934865.671 553186.043 5934865.076 </gml:posList>
            </gml:LineStringSegment>
            <gml:ArcString interpolation="circularArc3Points" numArc="1">
              <gml:posList srsName="EPSG:25832">553186.043 5934865.076 553184.207657243 5934865.39815112 553182.732 5934866.536 
</gml:posList>
            </gml:ArcString>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553182.732 5934866.536 553178.731 5934871.214 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553178.731 5934871.214 553174.627 5934878.962 </gml:posList>
            </gml:LineStringSegment>
            <gml:LineStringSegment interpolation="linear">
              <gml:posList srsName="EPSG:25832">553174.627 5934878.962 553183.625 5934883.814 </gml:posList>
            </gml:LineStringSegment>
          </gml:segments>
        </gml:Curve>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_db87cee1-192c-436d-a46e-14c48026de87">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553179.999 5934844.368</gml:lowerCorner>
          <gml:upperCorner>553197.594 5934860.432</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0068f757-a3ff-427d-9839-b656aa85cefd" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_F775CF4B-9056-4A72-A754-408288038DA8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553193.55 5934846.503 553192.092 5934848.623 553197.594 5934852.408 
553192.072 5934860.432 553184.164 5934854.979 553185.733 5934852.703 
553179.999 5934848.75 553185.004 5934845.532 553190.446 5934844.368 
553193.55 5934846.503 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0068f757-a3ff-427d-9839-b656aa85cefd">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553189.107 5934851.872</gml:lowerCorner>
          <gml:upperCorner>553189.107 5934851.872</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_db87cee1-192c-436d-a46e-14c48026de87" />
      <xplan:position>
        <gml:Point gml:id="Gml_0E3DE734-8159-49F9-A123-3DB465A43842" srsName="EPSG:25832">
          <gml:pos>553189.107 5934851.872</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_9c66df6a-37d6-43ae-9926-6b8dfbec35a0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553161.461 5934901.86</gml:lowerCorner>
          <gml:upperCorner>553189.986 5934927.765</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_163B4196-BA78-48F8-AFA0-3680EAFD5ECD" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553189.986 5934907.297 553177.838 5934917.678 553173.99 5934920.98 
553170.033 5934924.372 553166.17 5934927.765 553161.461 5934922.405 
553165.324 5934919.012 553169.285 5934915.603 553173.178 5934912.251 
553185.339 5934901.86 553189.986 5934907.297 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_32423208-6f19-464b-922d-da5f28ee95fc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553085.265 5935046.053</gml:lowerCorner>
          <gml:upperCorner>553101.664 5935062.584</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_deb2b7ef-0c45-4773-bde6-d6b9666eaceb" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0001A205-0F99-434E-9B87-BC0AD1695650" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553095.738 5935062.584 553085.265 5935057.436 553087.436 5935053.023 
553088.732 5935053.66 553092.475 5935046.053 553101.664 5935050.545 
553095.738 5935062.584 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_deb2b7ef-0c45-4773-bde6-d6b9666eaceb">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553093.0771 5935056.4174</gml:lowerCorner>
          <gml:upperCorner>553093.0771 5935056.4174</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_32423208-6f19-464b-922d-da5f28ee95fc" />
      <xplan:position>
        <gml:Point gml:id="Gml_D1785220-3C39-4793-9AF0-7AF6251AFDBF" srsName="EPSG:25832">
          <gml:pos>553093.0771 5935056.4174</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_5285e6c7-408b-4997-bd5b-77c26cc06655">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553010.759 5934964.661</gml:lowerCorner>
          <gml:upperCorner>553025.969 5934979.633</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_4E41A2CC-3547-48DF-8F6B-D4F89D00094C" srsName="EPSG:25832">
          <gml:posList>553025.969 5934973.411 553017.028 5934979.633 553010.759 5934971.166 
553019.544 5934964.661 553025.969 5934973.411 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_6257dc81-b135-4c5a-840e-027810488711">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553102.403 5934913.037</gml:lowerCorner>
          <gml:upperCorner>553116.196 5934923.937</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_5E90C6B3-B408-47DE-999F-F4AC30C94157" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553114.916 5934913.237 553116.196 5934916.245 553111.901 5934918.073 
553112.85 5934920.301 553103.961 5934923.937 553102.403 5934918.623 
553114.831 5934913.037 553114.916 5934913.237 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_a14c02b4-e624-4ebc-80be-358a28d6884a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553158.834 5934973.847</gml:lowerCorner>
          <gml:upperCorner>553173.912 5935017.085</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_0BDB3EBF-AE82-4785-BECC-0A36B16F3E9E" srsName="EPSG:25832">
          <gml:posList>553158.834 5934975.654 553168.863 5934973.847 553168.921 5934975.398 
553169.074 5934978.061 553169.567 5934985.588 553173.912 5935015.05 
553165.374 5935017.085 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_2bd87674-56e8-4e88-a609-1ffc31d26707">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552944.617 5934946.49</gml:lowerCorner>
          <gml:upperCorner>552964.43 5934962.774</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_99080673-25D1-4E9A-A705-6F5932462DBB" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">552964.43 5934955.159 552949.422 5934962.774 552946.855 5934958.269 
552944.617 5934954.342 552959.993 5934946.49 552964.43 5934955.159 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_668c1a19-3b86-41b9-b044-1eb4c73069ad">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552989.494 5934906.446</gml:lowerCorner>
          <gml:upperCorner>553025.618 5934940.16</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_c2b1fe14-d223-4cdb-8d3d-03deb21f43d7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0cd4f1d9-2e4c-4d89-ad70-f6b7e8a0157b" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_29dc4c70-596c-4b31-89b0-2031949f0a81" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_F21BA8CF-449C-4B29-9AE3-C53EE109B47A" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_FB3A6240-446E-4B38-95C3-2343FBD60E22" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553023.564 5934919.662 553025.618 5934921.673 553013.438 5934930.777 
553002.666 5934938.825 552992.007 5934940.16 552989.494 5934927.822 
553010.063 5934906.446 553023.564 5934919.662 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>5</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:Z>2</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_c2b1fe14-d223-4cdb-8d3d-03deb21f43d7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552996.23 5934935.077</gml:lowerCorner>
          <gml:upperCorner>552996.23 5934935.077</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_668c1a19-3b86-41b9-b044-1eb4c73069ad" />
      <xplan:position>
        <gml:Point gml:id="Gml_E324C7A4-0D9F-47D1-AD6B-C87C54D8F8DA" srsName="EPSG:25832">
          <gml:pos>552996.23 5934935.077</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0cd4f1d9-2e4c-4d89-ad70-f6b7e8a0157b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553006.322 5934921.869</gml:lowerCorner>
          <gml:upperCorner>553006.322 5934921.869</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_668c1a19-3b86-41b9-b044-1eb4c73069ad" />
      <xplan:position>
        <gml:Point gml:id="Gml_A1DF69B3-A6CA-4CEE-94BC-BB5FB15DA686" srsName="EPSG:25832">
          <gml:pos>553006.322 5934921.869</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_29dc4c70-596c-4b31-89b0-2031949f0a81">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553002.566 5934935.084</gml:lowerCorner>
          <gml:upperCorner>553002.566 5934935.084</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_668c1a19-3b86-41b9-b044-1eb4c73069ad" />
      <xplan:position>
        <gml:Point gml:id="Gml_6B169EEB-8020-4706-9EF9-0B138E6B548F" srsName="EPSG:25832">
          <gml:pos>553002.566 5934935.084</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_F21BA8CF-449C-4B29-9AE3-C53EE109B47A">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553001.2818 5934928.656</gml:lowerCorner>
          <gml:upperCorner>553001.2818 5934928.656</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_668c1a19-3b86-41b9-b044-1eb4c73069ad" />
      <xplan:position>
        <gml:Point gml:id="Gml_36EEF6A0-2EF9-49AE-A12D-3B3197E6E87A" srsName="EPSG:25832">
          <gml:pos>553001.2818 5934928.656</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_ca14e932-4e1b-4f7b-b978-2e9e7be47f0a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553135.853 5934998.472</gml:lowerCorner>
          <gml:upperCorner>553151.075 5935011.831</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_1A5CD80A-8AB5-4FDE-85A1-E59E68E7E3C0" srsName="EPSG:25832">
          <gml:posList>553151.075 5935011.177 553147.804 5935011.831 553135.853 5935001.3 
553138.214 5934998.472 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_91755c97-3300-46cf-9d30-e3664156df35">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553131.82 5934893.738</gml:lowerCorner>
          <gml:upperCorner>553147.821 5934908.683</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_E9E82915-5A4F-4315-93F6-4F9949B698C0" srsName="EPSG:25832">
          <gml:posList>553142.676 5934908.683 553131.82 5934901.126 553136.969 5934893.738 
553147.821 5934901.269 553142.676 5934908.683 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_e6567b66-6cbf-4f88-9238-374aebaaccd4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553170.008 5934885.949</gml:lowerCorner>
          <gml:upperCorner>553180.171 5934896.838</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_e241e8e4-8b12-4574-a61d-a8ab8dc7b739" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_1EC49E51-A25C-4B5C-BC10-6EC110BD5FED" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553180.171 5934889.269 553175.919 5934896.838 553170.008 5934893.517 
553174.261 5934885.949 553180.171 5934889.269 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_e241e8e4-8b12-4574-a61d-a8ab8dc7b739">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553175.09 5934891.393</gml:lowerCorner>
          <gml:upperCorner>553175.09 5934891.393</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e6567b66-6cbf-4f88-9238-374aebaaccd4" />
      <xplan:position>
        <gml:Point gml:id="Gml_864175D6-9016-443D-B2B6-7BF2A6D6C37D" srsName="EPSG:25832">
          <gml:pos>553175.09 5934891.393</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_1af950c4-47ae-46e8-a63f-c902e7a4639a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553138.214 5934990.199</gml:lowerCorner>
          <gml:upperCorner>553153.206 5935011.177</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_6b00776a-2bd0-4725-8535-b8eee96e2708" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_23D126FC-E6CB-4E50-86EB-294CA0E4D61E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553146.835 5934992.989 553153.206 5935010.751 553151.075 5935011.177 
553146.108 5935003.993 553138.214 5934998.472 553139.724 5934996.663 
553139.475 5934996.411 553144.048 5934990.199 553146.835 5934992.989 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>3</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_6b00776a-2bd0-4725-8535-b8eee96e2708">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553144.715 5934998.325</gml:lowerCorner>
          <gml:upperCorner>553144.715 5934998.325</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1af950c4-47ae-46e8-a63f-c902e7a4639a" />
      <xplan:position>
        <gml:Point gml:id="Gml_2DFA1574-988B-486A-A4C9-1A2C19E56E83" srsName="EPSG:25832">
          <gml:pos>553144.715 5934998.325</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_3b318736-4cfb-473d-a702-a92c5ea3d41d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552918.107 5934978.538</gml:lowerCorner>
          <gml:upperCorner>552934.286 5934994.296</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_82CE2BE8-7215-407C-B840-F5B57FD15F55" srsName="EPSG:25832">
          <gml:posList>552924.796 5934994.296 552918.107 5934986.014 552927.372 5934978.538 
552934.286 5934986.632 552924.796 5934994.296 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_f86f8524-4af4-4542-b742-0e7fbcbc5fb9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553196.179 5934878.096</gml:lowerCorner>
          <gml:upperCorner>553209.623 5934891.088</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_94B68855-294E-4928-BB06-2633012FEC87" srsName="EPSG:25832">
          <gml:posList>553209.623 5934884.266 553204.637 5934891.088 553196.179 5934884.905 
553201.156 5934878.096 553209.623 5934884.266 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_98831a84-4c4c-46f7-9fcd-6ebae8166715">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553105.216 5935000.04</gml:lowerCorner>
          <gml:upperCorner>553140.097 5935055.683</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_38ee1065-4767-4030-8485-91c7303c4525" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_0aeddea9-203f-4693-88dc-635260105501" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_614a7efe-b0a8-496a-8d9d-84c83be1322e" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_E6EE95D3-7B20-41E1-8AB1-060C221E693F" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_86BECCA6-6E27-41F2-BE77-277EFBA6E96E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553140.097 5935016.377 553137.515 5935025.234 553121.441 5935055.683 
553120.625 5935048.565 553116.929 5935038.969 553107.402 5935029.38 
553105.216 5935024.702 553106.13 5935019.962 553110.551 5935013.998 
553117.73 5935007.437 553131.071 5935000.04 553140.097 5935016.377 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>6</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:Z>3</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_38ee1065-4767-4030-8485-91c7303c4525">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553127.112 5935016.246</gml:lowerCorner>
          <gml:upperCorner>553127.112 5935016.246</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98831a84-4c4c-46f7-9fcd-6ebae8166715" />
      <xplan:position>
        <gml:Point gml:id="Gml_2A6225F0-2DF9-4C4D-8B4D-9A0DD603E3A4" srsName="EPSG:25832">
          <gml:pos>553127.112 5935016.246</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_0aeddea9-203f-4693-88dc-635260105501">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553123.21 5935009.692</gml:lowerCorner>
          <gml:upperCorner>553123.21 5935009.692</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98831a84-4c4c-46f7-9fcd-6ebae8166715" />
      <xplan:position>
        <gml:Point gml:id="Gml_7DDB399F-2DC9-4F91-8E10-5870DC8BAD8A" srsName="EPSG:25832">
          <gml:pos>553123.21 5935009.692</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_614a7efe-b0a8-496a-8d9d-84c83be1322e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553134.257 5935016.272</gml:lowerCorner>
          <gml:upperCorner>553134.257 5935016.272</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98831a84-4c4c-46f7-9fcd-6ebae8166715" />
      <xplan:position>
        <gml:Point gml:id="Gml_33712409-5B74-451F-9FA9-02F73283BF4C" srsName="EPSG:25832">
          <gml:pos>553134.257 5935016.272</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_E6EE95D3-7B20-41E1-8AB1-060C221E693F">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553122.3263 5935026.0737</gml:lowerCorner>
          <gml:upperCorner>553122.3263 5935026.0737</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_98831a84-4c4c-46f7-9fcd-6ebae8166715" />
      <xplan:position>
        <gml:Point gml:id="Gml_16C00405-5A08-4ADE-90C2-C2C1C4B606B4" srsName="EPSG:25832">
          <gml:pos>553122.3263 5935026.0737</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_305dc3da-3e3d-4168-895a-81ebb20b3a9b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553138.214 5934990.199</gml:lowerCorner>
          <gml:upperCorner>553153.206 5935011.177</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_038AC401-1E50-4A9B-BC1F-933788874924" srsName="EPSG:25832">
          <gml:posList>553153.206 5935010.751 553151.075 5935011.177 553146.108 5935003.993 
553138.214 5934998.472 553139.724 5934996.663 553139.475 5934996.411 
553144.048 5934990.199 553146.835 5934992.989 553153.206 5935010.751 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_95b99c64-cb7c-4f0b-aa7a-a9bb794a5dbc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553184.306 5934926.641</gml:lowerCorner>
          <gml:upperCorner>553196.787 5934939.221</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_6d84fc57-f194-46cd-b2f0-93f248d3e7d4" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_770817FE-548A-4A78-AA7C-F501B21803CA" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553196.787 5934932.158 553190.37 5934939.221 553184.306 5934933.74 
553190.72 5934926.641 553196.787 5934932.158 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_6d84fc57-f194-46cd-b2f0-93f248d3e7d4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553190.561 5934933.149</gml:lowerCorner>
          <gml:upperCorner>553190.561 5934933.149</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_95b99c64-cb7c-4f0b-aa7a-a9bb794a5dbc" />
      <xplan:position>
        <gml:Point gml:id="Gml_F1868A53-2AC7-4B0D-906C-00008D898CF9" srsName="EPSG:25832">
          <gml:pos>553190.561 5934933.149</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_583254d5-406b-4346-8ca2-8d41bd39ed52">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553163.577 5934948.637</gml:lowerCorner>
          <gml:upperCorner>553175.176 5934959.715</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_152EA9CF-A2D3-4712-8414-925BCC4BD261" srsName="EPSG:25832">
          <gml:posList>553175.176 5934953.429 553171.25 5934959.715 553163.577 5934954.921 
553167.502 5934948.637 553175.176 5934953.429 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_e4b5df11-6053-40a5-939c-2af5af7062be">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553124.822 5934874.942</gml:lowerCorner>
          <gml:upperCorner>553135.487 5934885.006</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_23e92186-a7c8-45cd-ac41-8f8cb24ec00b" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_67D47AC1-3D53-46EF-A0F3-9CA7D6B810E7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553135.487 5934879.538 553131.972 5934885.006 553124.822 5934880.409 
553128.337 5934874.942 553135.487 5934879.538 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_23e92186-a7c8-45cd-ac41-8f8cb24ec00b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553130.155 5934879.974</gml:lowerCorner>
          <gml:upperCorner>553130.155 5934879.974</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_e4b5df11-6053-40a5-939c-2af5af7062be" />
      <xplan:position>
        <gml:Point gml:id="Gml_91D27774-2671-4094-BD37-8A7A526C1586" srsName="EPSG:25832">
          <gml:pos>553130.155 5934879.974</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_696d6517-a621-444d-8969-293af608be8e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553215.039 5934893.432</gml:lowerCorner>
          <gml:upperCorner>553227.73 5934905.295</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_288c4114-5726-4343-b414-a6cb246b99a0" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_491C5B1E-F262-4D5F-801B-EB0FC324B315" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553227.73 5934900.365 553224.02 5934905.295 553215.039 5934898.534 
553218.521 5934893.432 553227.73 5934900.365 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_288c4114-5726-4343-b414-a6cb246b99a0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553221.554 5934899.14</gml:lowerCorner>
          <gml:upperCorner>553221.554 5934899.14</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_696d6517-a621-444d-8969-293af608be8e" />
      <xplan:position>
        <gml:Point gml:id="Gml_ACC8FDA9-4F2A-40C2-BF40-D76223C50895" srsName="EPSG:25832">
          <gml:pos>553221.554 5934899.14</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_15cff1c8-2e8d-4ab6-b5be-da0d72f05f43">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553068.719 5934906.724</gml:lowerCorner>
          <gml:upperCorner>553100.376 5934937.073</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_89AE92D2-771A-4AE0-BBD2-527FB34F99D4" srsName="EPSG:25832">
          <gml:posList>553077.824 5934906.916 553077.712 5934912.206 553086.713 5934912.385 
553086.73 5934916.566 553095.717 5934916.619 553095.549 5934924.263 
553095.474 5934927.624 553100.376 5934927.739 553100.164 5934937.073 
553086.305 5934936.735 553086.689 5934923.789 553086.698 5934922.176 
553077.503 5934921.986 553077.601 5934917.426 553068.719 5934917.234 
553068.953 5934906.724 553077.824 5934906.916 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_0f6191d0-c35c-4618-bc0f-5d73ceb973b8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553116.322 5934922.09</gml:lowerCorner>
          <gml:upperCorner>553139.99 5934936.588</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_394E3629-9CCB-4ADB-B510-5017AFC5F317" srsName="EPSG:25832">
          <gml:posList>553116.322 5934922.09 553133.461 5934927.565 553139.56 5934929.513 
553139.99 5934936.588 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_77276cf3-8acc-4c38-b1d3-414b16a049f6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553209.946 5935016.296</gml:lowerCorner>
          <gml:upperCorner>553225.674 5935035.876</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_73F52B7F-5A8C-4D9D-91BE-D45F8FABEC94" srsName="EPSG:25832">
          <gml:posList>553225.48 5935016.296 553225.674 5935034.591 553221.168 5935034.637 
553221.18 5935035.815 553214.929 5935035.876 553214.917 5935034.662 
553210.12 5935034.662 553209.946 5935016.465 553225.48 5935016.296 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_14900c93-f8a4-4b7c-aeb8-11f0a9349df7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553144.048 5934982.848</gml:lowerCorner>
          <gml:upperCorner>553156.382 5935010.751</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_83E113B8-D22F-4DF8-BE8E-A3676C2F6EE3" srsName="EPSG:25832">
          <gml:posList>553144.048 5934990.199 553149.427 5934982.848 553153.649 5934983.666 
553154.809 5935001.169 553156.146 5935007.135 553156.382 5935010.117 
553153.206 5935010.751 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_f70db4c1-0164-40a0-a61f-896a69703980">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553193.008 5934917.15</gml:lowerCorner>
          <gml:upperCorner>553204.267 5934928.503</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_3948C826-9324-4691-8350-B1067D63EA23" srsName="EPSG:25832">
          <gml:posList>553198.695 5934928.503 553193.008 5934923.951 553198.675 5934917.15 
553204.267 5934921.795 553198.695 5934928.503 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_2f84dff8-fef6-4c7b-9a3f-d92b8287335f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553097.338 5934901.818</gml:lowerCorner>
          <gml:upperCorner>553109.02 5934913.998</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_9EF70031-2417-46AA-B3A7-DBBC7A14962A" srsName="EPSG:25832">
          <gml:posList>553101.359 5934903.52 553104.97 5934901.818 553109.02 5934910.398 
553101.386 5934913.998 553097.338 5934905.415 553101.359 5934903.52 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_4ad531e0-a314-4dbb-b75e-d81df4d1c145">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553170.008 5934885.949</gml:lowerCorner>
          <gml:upperCorner>553180.171 5934896.838</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_E60D40DA-85FA-4803-A8B9-A4F5AFF37C34" srsName="EPSG:25832">
          <gml:posList>553174.261 5934885.949 553180.171 5934889.269 553175.919 5934896.838 
553170.008 5934893.517 553174.261 5934885.949 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_f73ba642-f8a0-40e8-ba36-c74cf66ea8dc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553046.32 5934890.284</gml:lowerCorner>
          <gml:upperCorner>553062.824 5934901.884</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_987FD61A-61D6-4B20-AC29-ED01B4DCF93F" srsName="EPSG:25832">
          <gml:posList>553047.193 5934899.473 553046.32 5934896.159 553048.761 5934895.517 
553048.243 5934893.551 553048.131 5934893.128 553058.877 5934890.284 
553059.028 5934890.855 553059.699 5934893.393 553061.953 5934892.799 
553062.824 5934896.098 553060.552 5934896.696 553061.165 5934899.023 
553050.285 5934901.884 553049.491 5934898.868 553047.193 5934899.473 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_256d1c29-a554-4f7c-8999-40760385d80b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552878.613 5934957.536</gml:lowerCorner>
          <gml:upperCorner>552951.709 5935034.204</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_6B284A10-3E69-4B8C-9ECE-41BA3F090C89" srsName="EPSG:25832">
          <gml:posList>552951.709 5934976.337 552951.234 5934974.356 552934.511 5934957.536 
552907.864 5934989.746 552887.269 5935017.351 552886.39 5935018.797 
552878.613 5935031.589 552881.056 5935034.204 552894.064 5935023.516 
552908.181 5935011.64 552930.917 5934992.515 552951.709 5934976.337 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_ed45178a-7f4c-46af-99b7-b84bde4b80bc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553090.672 5934966.99</gml:lowerCorner>
          <gml:upperCorner>553096.432 5934982.29</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_DB24532F-F106-45A3-ABF3-B88781626D87" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553096.432 5934980.782 553091.292 5934982.29 553090.672 5934967.572 
553095.726 5934966.99 553096.432 5934980.782 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_d8d0aea5-c6da-424b-a736-375d0b267194">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553176.754 5934890.502</gml:lowerCorner>
          <gml:upperCorner>553240.721 5934947.919</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_4b53c49a-63ef-4de6-a1e4-84874d90692e" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_543955f5-5c5f-416a-8de8-711003dd0736" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_FF90141C-327E-4477-BCC3-F0924685F026" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_7EA60D7E-8804-4C4A-A356-C028B4978B55" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_DA69EEEF-2B45-4358-899B-ED879D902043" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553227.184 5934925.38 553215.029 5934933.212 553210.329 5934936.179 
553198.992 5934947.919 553190.073 5934940.995 553176.754 5934932.327 
553178.548 5934930.234 553182.931 5934925.566 553187.964 5934921.061 
553196.604 5934912.688 553197.095 5934912.938 553197.366 5934912.538 
553197.458 5934912.244 553206.243 5934901.709 553210.766 5934896.192 
553212.496 5934893.811 553213.947 5934892.182 553215.367 5934891.142 
553216.457 5934890.731 553217.807 5934890.502 553219.476 5934890.661 
553220.926 5934890.99 553221.753 5934891.379 553223.027 5934892.152 
553225.005 5934893.858 553225.945 5934895.043 553229.571 5934901.985 
553232.334 5934905.994 553240.721 5934917.011 553229.109 5934924.689 
553228.929 5934924.441 553227.184 5934925.38 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_4b53c49a-63ef-4de6-a1e4-84874d90692e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553214.388 5934921.211</gml:lowerCorner>
          <gml:upperCorner>553214.388 5934921.211</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d8d0aea5-c6da-424b-a736-375d0b267194" />
      <xplan:position>
        <gml:Point gml:id="Gml_5C57D77F-116F-4A3E-8CC1-5FAE8E2B8956" srsName="EPSG:25832">
          <gml:pos>553214.388 5934921.211</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_543955f5-5c5f-416a-8de8-711003dd0736">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553205.504 5934931.371</gml:lowerCorner>
          <gml:upperCorner>553205.504 5934931.371</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d8d0aea5-c6da-424b-a736-375d0b267194" />
      <xplan:position>
        <gml:Point gml:id="Gml_11B0F185-1FD7-45AD-91B2-449FFC94CE39" srsName="EPSG:25832">
          <gml:pos>553205.504 5934931.371</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_FF90141C-327E-4477-BCC3-F0924685F026">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553209.4494 5934926.5328</gml:lowerCorner>
          <gml:upperCorner>553209.4494 5934926.5328</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d8d0aea5-c6da-424b-a736-375d0b267194" />
      <xplan:position>
        <gml:Point gml:id="Gml_06EE2B35-A273-4FE9-AB8C-F524E38F31FA" srsName="EPSG:25832">
          <gml:pos>553209.4494 5934926.5328</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_7EA60D7E-8804-4C4A-A356-C028B4978B55">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553222.6786 5934913.8328</gml:lowerCorner>
          <gml:upperCorner>553222.6786 5934913.8328</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d8d0aea5-c6da-424b-a736-375d0b267194" />
      <xplan:position>
        <gml:Point gml:id="Gml_B216C4F8-3EC2-4F17-B487-1F9719D33999" srsName="EPSG:25832">
          <gml:pos>553222.6786 5934913.8328</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_fa5531a6-12f4-4cdd-a541-a93a8c83d3ec">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553154.556 5934877.175</gml:lowerCorner>
          <gml:upperCorner>553165.791 5934890.868</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_BCB6B54C-44BC-4B4E-A23D-51E211A40327" srsName="EPSG:25832">
          <gml:posList>553154.556 5934887.942 553159.807 5934877.175 553165.791 5934880.118 
553160.506 5934890.868 553154.556 5934887.942 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_25a05ebc-4379-4f60-9f3a-e254f1c575ea">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553146.487 5935053.345</gml:lowerCorner>
          <gml:upperCorner>553173.3 5935069.538</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_44B435A8-B31C-43DA-843E-0BAAFBDF5FDB" srsName="EPSG:25832">
          <gml:posList>553159.342 5935060.578 553160.168 5935060.673 553173.3 5935062.19 
553172.286 5935069.538 553159.297 5935067.994 553146.487 5935066.471 
553148.26 5935053.345 553160.013 5935054.772 553159.342 5935060.578 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_c77a8197-037b-4698-8e3a-dfb5d3b28698">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553091.801 5934890.277</gml:lowerCorner>
          <gml:upperCorner>553122.427 5934917.698</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_7a9ad80f-d266-4ca2-a60e-ad58ec9f3499" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_b3268d0a-e7af-4eec-9f3b-07d7e92391cf" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_92128F9E-EC87-40ED-A4C1-7C155F99ECA9" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_2D00037E-777E-4FDA-BAF9-04BA4E0A5298" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_D64D9C5A-30E5-42C1-BE6B-5CD7851813A8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553108.47 5934893.119 553122.427 5934903.263 553114.928 5934911.316 
553113.658 5934912.004 553110.135 5934913.501 553101.537 5934917.698 
553100.553 5934916.647 553091.801 5934907.291 553104.56 5934890.277 
553108.47 5934893.119 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_7a9ad80f-d266-4ca2-a60e-ad58ec9f3499">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553111.8145 5934901.9536</gml:lowerCorner>
          <gml:upperCorner>553111.8145 5934901.9536</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c77a8197-037b-4698-8e3a-dfb5d3b28698" />
      <xplan:position>
        <gml:Point gml:id="Gml_077F107D-0B1C-4A6D-A59A-C4EE491CA82A" srsName="EPSG:25832">
          <gml:pos>553111.8145 5934901.9536</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_b3268d0a-e7af-4eec-9f3b-07d7e92391cf">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553100.5753 5934907.033</gml:lowerCorner>
          <gml:upperCorner>553100.5753 5934907.033</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c77a8197-037b-4698-8e3a-dfb5d3b28698" />
      <xplan:position>
        <gml:Point gml:id="Gml_3B0E578C-1408-4F97-9BCC-C633FA911374" srsName="EPSG:25832">
          <gml:pos>553100.5753 5934907.033</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_92128F9E-EC87-40ED-A4C1-7C155F99ECA9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553105.9762 5934896.9075</gml:lowerCorner>
          <gml:upperCorner>553105.9762 5934896.9075</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c77a8197-037b-4698-8e3a-dfb5d3b28698" />
      <xplan:position>
        <gml:Point gml:id="Gml_46E448A4-E29F-4311-B34E-009B4B9D8347" srsName="EPSG:25832">
          <gml:pos>553105.9762 5934896.9075</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_2D00037E-777E-4FDA-BAF9-04BA4E0A5298">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553111.7309 5934907.0145</gml:lowerCorner>
          <gml:upperCorner>553111.7309 5934907.0145</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_c77a8197-037b-4698-8e3a-dfb5d3b28698" />
      <xplan:position>
        <gml:Point gml:id="Gml_D9638D5D-49B6-40AA-9278-D7F19F407F4D" srsName="EPSG:25832">
          <gml:pos>553111.7309 5934907.0145</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_0b52a965-fd72-443a-a75d-a8d693891a28">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553177.821 5934868.482</gml:lowerCorner>
          <gml:upperCorner>553188.878 5934881.261</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_9d2c1bc9-d41a-4ac2-b5d6-3cc95c2b3dd2" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_AE23667C-3C01-4C0E-9009-D3CAB200431E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553188.878 5934871.688 553183.876 5934881.261 553177.821 5934878.096 
553182.743 5934868.482 553188.878 5934871.688 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_9d2c1bc9-d41a-4ac2-b5d6-3cc95c2b3dd2">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553183.8258 5934878.2145</gml:lowerCorner>
          <gml:upperCorner>553183.8258 5934878.2145</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0b52a965-fd72-443a-a75d-a8d693891a28" />
      <xplan:position>
        <gml:Point gml:id="Gml_CAD79C1A-14D3-4D6C-B37A-98F0397848E4" srsName="EPSG:25832">
          <gml:pos>553183.8258 5934878.2145</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
      <xplan:skalierung>0.5</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_23e4443d-c5d3-4494-adf7-c6ddca9d3042">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553166.521 5934947.057</gml:lowerCorner>
          <gml:upperCorner>553189.433 5934961.594</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:text>Sonstige Abgrenzung</xplan:text>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_6E73489D-5EFE-4C86-8C91-9B888FFE5208" srsName="EPSG:25832">
          <gml:posList>553166.521 5934947.057 553171.904 5934950.732 553177.876 5934954.81 
553179.39 5934955.248 553181.199 5934956.039 553182.819 5934957.126 
553189.433 5934961.594 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>9999</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_d7462686-461a-4804-9de7-f676e6dcfa76">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553142.762 5935029.425</gml:lowerCorner>
          <gml:upperCorner>553185.515 5935078.629</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_4bd8031d-89f4-4e22-a74b-15c30e65adfa" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_e612b54e-9346-4ff2-938e-66d45dd99129" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_EF610229-0F92-401F-AB98-23526A9F15B0" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_A0B47BE4-7C8A-4AFD-8BF5-DFCCDBD5E22C" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_3DD3B874-1B98-4678-A310-577853731611" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553173.71 5935048.054 553182.104 5935059.562 553184.935 5935065.063 
553185.515 5935068.051 553185.008 5935069.68 553184.083 5935078.629 
553142.762 5935074.21 553147.609 5935029.715 553151.158 5935029.663 
553167.249 5935029.425 553173.71 5935048.054 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>2000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_4bd8031d-89f4-4e22-a74b-15c30e65adfa">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553156.805 5935038.155</gml:lowerCorner>
          <gml:upperCorner>553156.805 5935038.155</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d7462686-461a-4804-9de7-f676e6dcfa76" />
      <xplan:position>
        <gml:Point gml:id="Gml_F7529C58-0055-493D-9E61-6C55ED21451A" srsName="EPSG:25832">
          <gml:pos>553156.805 5935038.155</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_e612b54e-9346-4ff2-938e-66d45dd99129">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553155.175 5935049.364</gml:lowerCorner>
          <gml:upperCorner>553155.175 5935049.364</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d7462686-461a-4804-9de7-f676e6dcfa76" />
      <xplan:position>
        <gml:Point gml:id="Gml_4727B740-DD7A-4602-A839-FCB4C16AE72D" srsName="EPSG:25832">
          <gml:pos>553155.175 5935049.364</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_EF610229-0F92-401F-AB98-23526A9F15B0">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553155.3993 5935043.9331</gml:lowerCorner>
          <gml:upperCorner>553155.3993 5935043.9331</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d7462686-461a-4804-9de7-f676e6dcfa76" />
      <xplan:position>
        <gml:Point gml:id="Gml_A0CB9709-8A17-46A8-9798-4F392886C58D" srsName="EPSG:25832">
          <gml:pos>553155.3993 5935043.9331</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_A0B47BE4-7C8A-4AFD-8BF5-DFCCDBD5E22C">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553167.5702 5935053.5904</gml:lowerCorner>
          <gml:upperCorner>553167.5702 5935053.5904</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d7462686-461a-4804-9de7-f676e6dcfa76" />
      <xplan:position>
        <gml:Point gml:id="Gml_5B51DE90-C5EC-4948-A5FD-3581620ACEF9" srsName="EPSG:25832">
          <gml:pos>553167.5702 5935053.5904</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_2d4640c5-adbd-40ad-9f97-c360ee8192d5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553124.226 5934905.036</gml:lowerCorner>
          <gml:upperCorner>553138.337 5934918.721</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_d6edbcef-8212-4a08-ada5-c43437074ad6" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_15BA4968-4533-4B7D-A71E-8BE84B48B6E0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553132.781 5934907.196 553132.768 5934907.218 553138.337 5934910.394 
553133.589 5934918.721 553124.226 5934913.382 553128.982 5934905.036 
553132.781 5934907.196 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_d6edbcef-8212-4a08-ada5-c43437074ad6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553130.3285 5934909.1892</gml:lowerCorner>
          <gml:upperCorner>553130.3285 5934909.1892</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_2d4640c5-adbd-40ad-9f97-c360ee8192d5" />
      <xplan:position>
        <gml:Point gml:id="Gml_9F7B03A5-8D3A-4BD1-AAC5-FD143BE5B5F7" srsName="EPSG:25832">
          <gml:pos>553130.3285 5934909.1892</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_9f80e9c6-1681-44c6-b9f8-62f030600dda">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553086.698 5934916.566</gml:lowerCorner>
          <gml:upperCorner>553086.73 5934922.176</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_842B8105-BF29-4A5E-8C43-ED4237B7B17C" srsName="EPSG:25832">
          <gml:posList>553086.73 5934916.566 553086.698 5934922.176 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_78ecd6a5-0ece-493e-af8f-189bdf8b6976">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553032.5 5934981.975</gml:lowerCorner>
          <gml:upperCorner>553091.729 5935034.119</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_2BE107FE-C0AA-4D7B-91B7-91AAFA3C474E" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_5C5481A0-6B77-4BBD-81FD-AAA1F6200BD8" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_263B7E60-BBAB-4FAD-8017-4C1C77845367" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_FFB24803-28F7-4EF3-8FC4-D1F60A40E85E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553082.284 5934991.392 553091.729 5934992.677 553081.048 5935001.594 
553048.283 5935034.119 553047.724 5935033.506 553043.308 5935028.187 
553041.991 5935026.6 553039.242 5935022.958 553034.177 5935016.092 
553032.5 5935013.818 553069.378 5934981.975 553082.284 5934991.392 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>3</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_2BE107FE-C0AA-4D7B-91B7-91AAFA3C474E">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553064.4687 5935004.1066</gml:lowerCorner>
          <gml:upperCorner>553064.4687 5935004.1066</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_78ecd6a5-0ece-493e-af8f-189bdf8b6976" />
      <xplan:position>
        <gml:Point gml:id="Gml_0F404C30-CC44-4D4F-9E04-A26225786DC1" srsName="EPSG:25832">
          <gml:pos>553064.4687 5935004.1066</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_5C5481A0-6B77-4BBD-81FD-AAA1F6200BD8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553064.9979 5934998.2858</gml:lowerCorner>
          <gml:upperCorner>553064.9979 5934998.2858</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_78ecd6a5-0ece-493e-af8f-189bdf8b6976" />
      <xplan:position>
        <gml:Point gml:id="Gml_DBD7FA53-8B00-46E2-91A3-9C8E9564BCB0" srsName="EPSG:25832">
          <gml:pos>553064.9979 5934998.2858</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_263B7E60-BBAB-4FAD-8017-4C1C77845367">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553068.3845 5934992.0416</gml:lowerCorner>
          <gml:upperCorner>553068.3845 5934992.0416</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_78ecd6a5-0ece-493e-af8f-189bdf8b6976" />
      <xplan:position>
        <gml:Point gml:id="Gml_C4499B57-1362-4EC7-82AB-C9051C2E0166" srsName="EPSG:25832">
          <gml:pos>553068.3845 5934992.0416</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_2d155575-31b1-4e01-a996-e1752c798084">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553176.754 5934932.327</gml:lowerCorner>
          <gml:upperCorner>553198.992 5934947.919</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_FDD6CBB4-58D0-4536-B98C-2C031BE22A83" srsName="EPSG:25832">
          <gml:posList>553176.754 5934932.327 553190.073 5934940.995 553198.992 5934947.919 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_153ef48b-a709-476d-bc85-71d1f0d05770">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553158.242 5934838.509</gml:lowerCorner>
          <gml:upperCorner>553203.114 5934867.382</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_bc5d5385-2b17-4047-8d7d-a092c9ec3f21" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_1cdd2da6-30b7-450d-ab4a-5af1f2fa298e" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_C32A3807-B3DF-4B45-8F19-61108319C391" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_5BCFE120-407E-4439-9B5B-201EFB858C1A" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_49DC2A1D-65B4-4D50-B004-97BCF4ADEB28" srsName="EPSG:25832">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_5EEBC64F-42E9-42CC-8D26-CE2681F66915" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553168.374 5934867.382 553158.242 5934860.856 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_11E6DCD1-ADF8-4B1B-8288-EF488930F104" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553158.242 5934860.856 553172.603 5934848.851 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8D1F79FC-438E-4C23-A73E-143AE505485D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553172.603 5934848.851 553180.775 5934843.278 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_2712D75B-E4C0-4A0E-82AB-1384FD0A5FD5" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553180.775 5934843.278 553180.481 5934842.873 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_99B392F9-9ACC-4A4F-8771-4BABA38A6D2D" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553180.481 5934842.873 553181.173 5934842.468 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0CC1DC04-F583-49F0-8923-70DB93754BCF" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553181.173 5934842.468 553182.22 5934842.051 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8BDB6BC8-D88E-47DE-9207-5ACA4A7DD291" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553182.22 5934842.051 553184.536 5934841.337 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E33D0329-AC38-46E7-9C02-56C244C9B85B" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553184.536 5934841.337 553190.987 5934840.533 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_C6A70D30-2BBA-4A40-BD9D-0E5D753AE006" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553190.987 5934840.533 553199.801 5934838.653 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_01892C26-039E-412A-B7DB-FD85B9A75AC2" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553199.801 5934838.653 553200.412 5934838.509 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_E16C3F2D-379B-4899-A11A-08B6DB352717" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553200.412 5934838.509 553201.016 5934838.666 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_3027AA76-FE6B-449B-A4B4-0473BCE102D6" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553201.016 5934838.666 553202.629 5934839.931 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_670440C6-3C20-4D74-89EF-DD7C24EF7250" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553202.629 5934839.931 553203.035 5934840.252 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_316B68D0-1BE7-4F90-8ED9-73B09751D2E1" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553203.035 5934840.252 553203.114 5934840.748 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:Curve gml:id="Gml_EC5E72B2-F964-4D6C-827F-C57578014145" srsName="EPSG:25832">
                  <gml:segments>
                    <gml:Arc interpolation="circularArc3Points" numArc="1">
                      <gml:posList srsName="EPSG:25832">553203.114 5934840.748 553199.739943437 5934848.44813307 553196.537 5934856.221 
</gml:posList>
                    </gml:Arc>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_566F5053-61E1-4627-85B9-8DD909CDE349" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553196.537 5934856.221 553194.33 5934858.414 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_B1D29D57-C6AF-44A0-803A-EAE992C21B07" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553194.33 5934858.414 553193.388 5934859.253 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6D99BDBC-D9B4-43DA-AF66-5DD96E0F782F" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553193.388 5934859.253 553191.385 5934860.375 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_6AADCC3A-C312-42AC-8F0B-7F620B3D6FA9" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553191.385 5934860.375 553184.677 5934855.336 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_8791A11D-E385-4B64-A33E-E7E87561A442" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553184.677 5934855.336 553181.257 5934852.962 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_0B4334CD-244A-4A7A-AE9D-CDCA0B87F686" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553181.257 5934852.962 553179.563 5934851.785 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
              <gml:curveMember>
                <gml:LineString gml:id="Gml_1E7F2529-B131-4833-88B7-6E871942BAF3" srsName="EPSG:25832">
                  <gml:posList srsName="EPSG:25832">553179.563 5934851.785 553168.374 5934867.382 </gml:posList>
                </gml:LineString>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>2000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_bc5d5385-2b17-4047-8d7d-a092c9ec3f21">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553167.122 5934860.305</gml:lowerCorner>
          <gml:upperCorner>553167.122 5934860.305</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_153ef48b-a709-476d-bc85-71d1f0d05770" />
      <xplan:position>
        <gml:Point gml:id="Gml_99D7DF28-4DFC-464D-A44A-6150B62BF18C" srsName="EPSG:25832">
          <gml:pos>553167.122 5934860.305</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_1cdd2da6-30b7-450d-ab4a-5af1f2fa298e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553174.327 5934851.148</gml:lowerCorner>
          <gml:upperCorner>553174.327 5934851.148</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_153ef48b-a709-476d-bc85-71d1f0d05770" />
      <xplan:position>
        <gml:Point gml:id="Gml_EE50678A-1F12-4953-9506-6C3081235B1F" srsName="EPSG:25832">
          <gml:pos>553174.327 5934851.148</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_C32A3807-B3DF-4B45-8F19-61108319C391">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553170.4782 5934855.9906</gml:lowerCorner>
          <gml:upperCorner>553170.4782 5934855.9906</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_153ef48b-a709-476d-bc85-71d1f0d05770" />
      <xplan:position>
        <gml:Point gml:id="Gml_B598AC0E-D58F-45EF-92AA-30BF992D0494" srsName="EPSG:25832">
          <gml:pos>553170.4782 5934855.9906</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_5BCFE120-407E-4439-9B5B-201EFB858C1A">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553196.8307 5934843.6081</gml:lowerCorner>
          <gml:upperCorner>553196.8307 5934843.6081</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_153ef48b-a709-476d-bc85-71d1f0d05770" />
      <xplan:position>
        <gml:Point gml:id="Gml_94DC4B64-28FA-4E3B-98B4-880504B50B65" srsName="EPSG:25832">
          <gml:pos>553196.8307 5934843.6081</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_17440e1c-61d7-4f42-8d82-db8f3e966d64">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553153.149 5934862.162</gml:lowerCorner>
          <gml:upperCorner>553161.928 5934871.055</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_EA72E8AA-7D08-4F61-B0A9-15B7825B3222" srsName="EPSG:25832">
          <gml:posList>553153.149 5934867.58 553156.849 5934862.162 553161.928 5934865.596 
553158.266 5934871.055 553153.149 5934867.58 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_5f1742df-b5af-439a-b518-a6487bb937a5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553143.047 5934942.598</gml:lowerCorner>
          <gml:upperCorner>553153.755 5934955.485</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_c43ab2ed-5bed-4051-b67f-5768e4d51f42" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_E3570AD8-C882-43B8-8D4A-01A27FAD2CD5" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553153.755 5934953.07 553146.562 5934955.485 553143.047 5934945.008 
553150.237 5934942.598 553153.755 5934953.07 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_c43ab2ed-5bed-4051-b67f-5768e4d51f42">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553148.881 5934950.472</gml:lowerCorner>
          <gml:upperCorner>553148.881 5934950.472</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5f1742df-b5af-439a-b518-a6487bb937a5" />
      <xplan:position>
        <gml:Point gml:id="Gml_0FC7C248-9E1C-4EE7-8D1A-52EA7EDB9D49" srsName="EPSG:25832">
          <gml:pos>553148.881 5934950.472</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_b87c872f-56da-4b8c-947a-f712ff4510e1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552944.617 5934946.49</gml:lowerCorner>
          <gml:upperCorner>552964.43 5934962.774</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_9B88EB47-3612-4894-B427-E9956A1F7E40" srsName="EPSG:25832">
          <gml:posList>552964.43 5934955.159 552949.422 5934962.774 552946.855 5934958.269 
552944.617 5934954.342 552959.993 5934946.49 552964.43 5934955.159 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_abdb3126-8e67-4e77-8c23-52261d30de29">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553130.869 5934818.851</gml:lowerCorner>
          <gml:upperCorner>553150.063 5934843.455</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_1CFF8CF9-9FDF-4639-BFBD-2342C47EC5E6" srsName="EPSG:25832">
          <gml:posList>553138.851 5934830.242 553134.044 5934825.888 553140.416 5934818.851 
553150.063 5934827.593 553135.724 5934843.455 553130.869 5934839.065 
553138.851 5934830.242 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_46a964e3-b714-4d26-a891-ebda886979cf">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553225.471 5934924.006</gml:lowerCorner>
          <gml:upperCorner>553244.104 5934939.315</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_476BE732-6D87-4161-B736-8B8DEDCB367A" srsName="EPSG:25832">
          <gml:posList>553238.558 5934933.168 553239.342 5934934.383 553235.209 5934936.971 
553234.461 5934935.811 553231.278 5934937.864 553231.158 5934939.315 
553228.861 5934939.166 553228.976 5934937.498 553225.471 5934932.064 
553230.15 5934929.046 553230.882 5934930.181 553233.379 5934928.578 
553232.643 5934927.437 553236.491 5934924.955 553237.23 5934926.106 
553240.502 5934924.006 553244.104 5934929.589 553238.558 5934933.168 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_0524efaf-b3e5-40f2-83bf-4d8563133620">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553158.234 5934947.057</gml:lowerCorner>
          <gml:upperCorner>553189.433 5934977.757</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ef5b831c-4ea9-40fc-9094-246688ac8f92" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_ce5f1622-46d2-419e-9a98-635cafc8eaea" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_63BFE4F5-01AC-4DF6-845E-7661CBDBF50C" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_FDCD2D62-F84F-43BB-BC98-E1FC5B1BA7D3" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C6376070-F748-437A-845B-8D6251ABC72E" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553180.889 5934977.757 553168.921 5934975.398 553168.863 5934973.847 
553158.834 5934975.654 553158.234 5934965.678 553161.945 5934958.374 
553161.764 5934955.9 553166.521 5934947.057 553171.904 5934950.732 
553177.876 5934954.81 553179.39 5934955.248 553181.199 5934956.039 
553182.819 5934957.126 553189.433 5934961.594 553184.472 5934969.866 
553180.889 5934977.757 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>1000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ef5b831c-4ea9-40fc-9094-246688ac8f92">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553174.125 5934972.53</gml:lowerCorner>
          <gml:upperCorner>553174.125 5934972.53</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.03</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0524efaf-b3e5-40f2-83bf-4d8563133620" />
      <xplan:position>
        <gml:Point gml:id="Gml_74482102-EF0B-4B04-9710-1331BE0FD1BB" srsName="EPSG:25832">
          <gml:pos>553174.125 5934972.53</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_ce5f1622-46d2-419e-9a98-635cafc8eaea">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553174.2985 5934967.6649</gml:lowerCorner>
          <gml:upperCorner>553174.2985 5934967.6649</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0524efaf-b3e5-40f2-83bf-4d8563133620" />
      <xplan:position>
        <gml:Point gml:id="Gml_9DA51277-FE77-4F16-85CB-8EB9E3B48AD5" srsName="EPSG:25832">
          <gml:pos>553174.2985 5934967.6649</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_63BFE4F5-01AC-4DF6-845E-7661CBDBF50C">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553178.0477 5934962.3134</gml:lowerCorner>
          <gml:upperCorner>553178.0477 5934962.3134</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0524efaf-b3e5-40f2-83bf-4d8563133620" />
      <xplan:position>
        <gml:Point gml:id="Gml_D43C1FBE-311A-4C31-8E2E-8C9B9957AC3A" srsName="EPSG:25832">
          <gml:pos>553178.0477 5934962.3134</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_FDCD2D62-F84F-43BB-BC98-E1FC5B1BA7D3">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553165.7446 5934961.1228</gml:lowerCorner>
          <gml:upperCorner>553165.7446 5934961.1228</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_0524efaf-b3e5-40f2-83bf-4d8563133620" />
      <xplan:position>
        <gml:Point gml:id="Gml_D34537BE-7347-4D1E-BDBC-0B83CEBB832F" srsName="EPSG:25832">
          <gml:pos>553165.7446 5934961.1228</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_8ebb5c68-7fe4-4d1a-996e-ccf5feaeaf37">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553131.071 5934975.217</gml:lowerCorner>
          <gml:upperCorner>553167.249 5935029.663</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_4a14944e-332a-4a77-b9d4-be9d45d9ec7b" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_90ffc140-b75b-43ca-b3e2-99e4c6813265" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_F094093A-5E0E-4063-B8A5-913E91C9731B" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_2C5E3FB1-A0BF-4438-A561-4E245BF4631C" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553164.607 5935021.81 553167.249 5935029.425 553151.158 5935029.663 
553150.719 5935024.482 553143.91 5935015.808 553137.515 5935025.234 
553140.097 5935016.377 553131.071 5935000.04 553137.583 5934996.006 
553143.103 5934989.253 553152.147 5934975.217 553156.437 5934975.389 
553157.106 5934979.841 553157.309 5934983.359 553161.69 5935013.361 
553164.607 5935021.81 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>3</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.3</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_4a14944e-332a-4a77-b9d4-be9d45d9ec7b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553157.318 5935024.985</gml:lowerCorner>
          <gml:upperCorner>553157.318 5935024.985</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.03</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ebb5c68-7fe4-4d1a-996e-ccf5feaeaf37" />
      <xplan:position>
        <gml:Point gml:id="Gml_3AD8C740-F8F4-4D83-83CA-FC88971561B6" srsName="EPSG:25832">
          <gml:pos>553157.318 5935024.985</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_90ffc140-b75b-43ca-b3e2-99e4c6813265">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553149.603 5935014.867</gml:lowerCorner>
          <gml:upperCorner>553149.603 5935014.867</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ebb5c68-7fe4-4d1a-996e-ccf5feaeaf37" />
      <xplan:position>
        <gml:Point gml:id="Gml_6E6F66E1-3608-42DA-9429-ACCDA9F39DEE" srsName="EPSG:25832">
          <gml:pos>553149.603 5935014.867</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_F094093A-5E0E-4063-B8A5-913E91C9731B">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553153.8118 5935019.9883</gml:lowerCorner>
          <gml:upperCorner>553153.8118 5935019.9883</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_8ebb5c68-7fe4-4d1a-996e-ccf5feaeaf37" />
      <xplan:position>
        <gml:Point gml:id="Gml_DF87F22A-F200-4ECD-A628-A4584EE207F6" srsName="EPSG:25832">
          <gml:pos>553153.8118 5935019.9883</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_b52de912-5178-496e-a3ce-7e32f40ce463">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>552996.101 5934938.648</gml:lowerCorner>
          <gml:upperCorner>553011.233 5934949.324</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0122EB9C-BE4A-4B84-B144-394637A551D5" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553011.233 5934944.058 552999.207 5934949.324 552996.101 5934942.236 
553002.185 5934939.574 553002.92 5934941.249 553008.862 5934938.648 
553011.233 5934944.058 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_03121dc5-dfcb-4640-8bbc-5dae50c40b6f">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553170.395 5934855.647</gml:lowerCorner>
          <gml:upperCorner>553182.888 5934869.329</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_31db0adc-dc7b-44b3-8450-e1e324c174e1" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_4529C3C5-C1A2-462D-91D6-A496D27806E7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553182.888 5934859.703 553175.987 5934869.329 553170.395 5934865.32 
553177.311 5934855.647 553182.888 5934859.703 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>1</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_31db0adc-dc7b-44b3-8450-e1e324c174e1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553176.632 5934862.468</gml:lowerCorner>
          <gml:upperCorner>553176.632 5934862.468</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_03121dc5-dfcb-4640-8bbc-5dae50c40b6f" />
      <xplan:position>
        <gml:Point gml:id="Gml_2080ECA7-1F0C-4E49-B076-F700AB6A51CC" srsName="EPSG:25832">
          <gml:pos>553176.632 5934862.468</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_b4f9f49a-0a3e-4837-bc59-fc9802bd6bd6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553140.101 5934852.063</gml:lowerCorner>
          <gml:upperCorner>553150.328 5934861.841</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D98616E7-2DDD-485E-A39C-1CD3AEB91EC1" srsName="EPSG:25832">
          <gml:posList>553150.328 5934856.438 553146.799 5934861.841 553140.101 5934857.467 
553143.63 5934852.063 553150.328 5934856.438 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_StrassenbegrenzungsLinie gml:id="GML_146890fb-71f0-4b47-a800-0c9b02852879">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553164.132 5935014.151</gml:lowerCorner>
          <gml:upperCorner>553225.953 5935070.404</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_55F1655E-8743-4664-A72F-71B29E1C01C0" srsName="EPSG:25832">
          <gml:posList>553164.132 5935014.151 553164.204 5935014.645 553164.343 5935014.935 
553165.374 5935017.085 553168.862 5935026.498 553176.261 5935047.118 
553184.408 5935058.141 553190.206 5935069.693 553209.094 5935069.728 
553225.953 5935070.404 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_StrassenbegrenzungsLinie>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_ef2793da-5ac0-49cb-82e2-67bd770492e9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553161.048 5934885.016</gml:lowerCorner>
          <gml:upperCorner>553183.177 5934904.255</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D46FE9AD-5891-48CF-90FF-099D204BCD58" srsName="EPSG:25832">
          <gml:posList>553183.177 5934886.615 553173.903 5934885.016 553169.372 5934893.251 
553165.037 5934898.982 553161.048 5934904.255 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_a2ef3c09-2d64-41a0-9f7c-21ff7ca6c256">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553068.183 5934916.566</gml:lowerCorner>
          <gml:upperCorner>553100.553 5934923.789</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_5A8006CC-DD14-4AAD-819D-B6AD59A37137" srsName="EPSG:25832">
          <gml:posList>553068.183 5934923.751 553086.689 5934923.789 553086.698 5934922.176 
553086.73 5934916.566 553095.717 5934916.619 553100.553 5934916.647 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>1000</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_b8040098-602f-4e55-a301-f54ebbd5527c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553117.29 5934947.604</gml:lowerCorner>
          <gml:upperCorner>553130.868 5934960.826</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_651206F2-BE1E-48B9-AE90-B82013142714" srsName="EPSG:25832">
          <gml:posList>553126.521 5934947.604 553130.058 5934955.668 553130.868 5934957.515 
553122.721 5934960.826 553117.29 5934948.542 553126.521 5934947.604 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_927927eb-407c-4b5f-a627-05059910272d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553136.869 5934959.209</gml:lowerCorner>
          <gml:upperCorner>553152.776 5934974.657</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_D5FE6CAF-B64A-41D7-84BC-150958B77CA1" srsName="EPSG:25832">
          <gml:posList>553150.281 5934970.732 553149.925 5934971.29 553142.813 5934974.657 
553136.869 5934962.484 553141.284 5934960.211 553142.288 5934962.25 
553149.78 5934959.209 553152.776 5934964.124 553150.281 5934970.732 
</gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BauGrenze gml:id="GML_d643f79a-03c2-4a87-b3fa-8abee779f018">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553049.399 5934915.189</gml:lowerCorner>
          <gml:upperCorner>553065.837 5934932.267</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_AF376A97-979A-4071-8421-57BF8ECA9CC5" srsName="EPSG:25832">
          <gml:posList>553065.837 5934927.293 553060.333 5934932.267 553049.399 5934920.155 
553054.904 5934915.189 553065.837 5934927.293 </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_BauGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_b130a89a-38db-488e-a2b7-c8ad4604c8b6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553018.342 5934968.139</gml:lowerCorner>
          <gml:upperCorner>553069.378 5935013.818</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_d732713d-6c1b-4298-abe9-1590e7bc5f08" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_dbdbfd4f-fa04-4ee4-8d1b-3a939bbb9b25" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_0163D672-E078-4524-874F-B73E68B230DC" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_C7FEFED8-0310-4054-982A-5360C7C7D194" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_847C7985-B83C-4906-9B35-C6943FCF1DC9" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553069.378 5934981.975 553032.5 5935013.818 553028.178 5935007.962 
553024.258 5935001.607 553023.548 5935000.455 553021.17 5934996.737 
553019.857 5934994.632 553018.771 5934992.782 553018.342 5934991.971 
553050.42 5934968.139 553069.378 5934981.975 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:MaxZahlWohnungen>2</xplan:MaxZahlWohnungen>
      <xplan:GRZ>0.2</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
      <xplan:bebauungsArt>2000</xplan:bebauungsArt>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_d732713d-6c1b-4298-abe9-1590e7bc5f08">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553045.883 5934980.529</gml:lowerCorner>
          <gml:upperCorner>553045.883 5934980.529</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.03.01</xplan:stylesheetId>
      <xplan:art>GRZ</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_b130a89a-38db-488e-a2b7-c8ad4604c8b6" />
      <xplan:position>
        <gml:Point gml:id="Gml_5ECB8425-9380-45B4-8B39-D73CD4B90B1F" srsName="EPSG:25832">
          <gml:pos>553045.883 5934980.529</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_dbdbfd4f-fa04-4ee4-8d1b-3a939bbb9b25">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553049.253 5934989.39</gml:lowerCorner>
          <gml:upperCorner>553049.253 5934989.39</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_04.01.02</xplan:stylesheetId>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_b130a89a-38db-488e-a2b7-c8ad4604c8b6" />
      <xplan:position>
        <gml:Point gml:id="Gml_193AA90C-43F3-48DB-84C6-0630C4767352" srsName="EPSG:25832">
          <gml:pos>553049.253 5934989.39</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_0163D672-E078-4524-874F-B73E68B230DC">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553047.8528 5934985.0037</gml:lowerCorner>
          <gml:upperCorner>553047.8528 5934985.0037</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>MaxZahlWohnungen</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_b130a89a-38db-488e-a2b7-c8ad4604c8b6" />
      <xplan:position>
        <gml:Point gml:id="Gml_CB92DC27-73F1-41E0-ADBF-4981FA1F5873" srsName="EPSG:25832">
          <gml:pos>553047.8528 5934985.0037</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_C7FEFED8-0310-4054-982A-5360C7C7D194">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553058.6478 5934982.0668</gml:lowerCorner>
          <gml:upperCorner>553058.6478 5934982.0668</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>bebauungsArt</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_b130a89a-38db-488e-a2b7-c8ad4604c8b6" />
      <xplan:position>
        <gml:Point gml:id="Gml_3E87141F-81D6-4B27-80A8-3720E48D9A03" srsName="EPSG:25832">
          <gml:pos>553058.6478 5934982.0668</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_NutzungsartenGrenze gml:id="GML_6cdd76d0-1a7f-4594-b3aa-a5a11fa9b9da">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553116.322 5934900.81</gml:lowerCorner>
          <gml:upperCorner>553143.986 5934927.565</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:text>Sonstige Abgrenzung</xplan:text>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_14FFE703-557E-4AA4-95A1-96ACD8830705" srsName="EPSG:25832">
          <gml:posList>553116.322 5934922.09 553133.461 5934927.565 553143.986 5934912.266 
553134.78 5934905.839 553130.58 5934902.906 553129.139 5934901.9 
553127.577 5934900.81 </gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:typ>9999</xplan:typ>
    </xplan:BP_NutzungsartenGrenze>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_UeberbaubareGrundstuecksFlaeche gml:id="GML_5e18029a-40c4-4a02-b339-273f8be7e02e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553163.577 5934948.637</gml:lowerCorner>
          <gml:upperCorner>553175.176 5934959.715</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_caf44381-5def-43a4-a85f-2d586a6cda01" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_6199C797-C021-472C-B572-ECAB87A2B502" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553175.176 5934953.429 553171.25 5934959.715 553163.577 5934954.921 
553167.502 5934948.637 553175.176 5934953.429 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:Z>2</xplan:Z>
    </xplan:BP_UeberbaubareGrundstuecksFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_caf44381-5def-43a4-a85f-2d586a6cda01">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553169.352 5934953.757</gml:lowerCorner>
          <gml:upperCorner>553169.352 5934953.757</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:stylesheetId>HHBplan_05.06.01</xplan:stylesheetId>
      <xplan:art>Z</xplan:art>
      
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_5e18029a-40c4-4a02-b339-273f8be7e02e" />
      <xplan:position>
        <gml:Point gml:id="Gml_25AE81CC-4463-4F37-AD63-0723BEAB11B7" srsName="EPSG:25832">
          <gml:pos>553169.352 5934953.757</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0</xplan:drehwinkel>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:LP_SchutzBestimmterTeileVonNaturUndLandschaft gml:id="GML_e6726f89-3240-44e3-8457-24f9e7cd261b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553198.38 5935008.185</gml:lowerCorner>
          <gml:upperCorner>553198.38 5935008.185</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>2000</xplan:rechtscharakter>
      <xplan:raumkonkretisierung>1000</xplan:raumkonkretisierung>
      <xplan:position>
        <gml:Point gml:id="Gml_534FD1A3-CAA6-40B0-92FA-C2CD5B46D1AA" srsName="EPSG:25832">
          <gml:pos>553198.38 5935008.185</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:artDerFestlegung>1300</xplan:artDerFestlegung>      
      <xplan:rechtsstandSchG>1000</xplan:rechtsstandSchG>
    </xplan:LP_SchutzBestimmterTeileVonNaturUndLandschaft>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:LP_SchutzBestimmterTeileVonNaturUndLandschaft gml:id="GML_c0aa7384-9d95-4121-b5a8-ce48ff595148">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>553165.374 5934917.011</gml:lowerCorner>
          <gml:upperCorner>553299.972 5935070.404</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_c1c51a4c-63bc-4b20-a8a4-a3f6d6683ae7" />
      <xplan:rechtscharakter>2000</xplan:rechtscharakter>
      <xplan:raumkonkretisierung>1000</xplan:raumkonkretisierung>
      <xplan:position>
        <gml:Polygon gml:id="Gml_B14CB78F-7D49-46F8-850E-C109E92682A3" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">553289.676 5934978.967 553261.183 5934997.286 553264.006 5935001.415 
553274.278 5935016.443 553299.972 5935051.125 553294.9752 5935054.3542 
553258.2483 5935066.594 553242.1183 5935067.0238 553230.74 5935070.32 
553225.953 5935070.404 553209.094 5935069.728 553190.206 5935069.693 
553184.408 5935058.141 553176.261 5935047.118 553168.862 5935026.498 
553165.374 5935017.085 553173.912 5935015.05 553169.567 5934985.588 
553169.074 5934978.061 553168.921 5934975.398 553180.889 5934977.757 
553184.472 5934969.866 553189.433 5934961.594 553194.696 5934954.625 
553198.992 5934947.919 553210.329 5934936.179 553215.029 5934933.212 
553227.184 5934925.38 553228.929 5934924.441 553229.109 5934924.689 
553240.721 5934917.011 553241.738 5934918.349 553251.227 5934930.652 
553259.312 5934940.966 553262.451 5934939.088 553277.975 5934949.65 
553271.471 5934954.505 553289.676 5934978.967 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>      
      <xplan:artDerFestlegung>1300</xplan:artDerFestlegung>
      <xplan:rechtsstandSchG>1000</xplan:rechtsstandSchG>
    </xplan:LP_SchutzBestimmterTeileVonNaturUndLandschaft>
  </gml:featureMember>
</xplan:XPlanAuszug>