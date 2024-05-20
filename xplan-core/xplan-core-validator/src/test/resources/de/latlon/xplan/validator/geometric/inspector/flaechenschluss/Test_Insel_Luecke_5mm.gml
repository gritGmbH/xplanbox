<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!--Erstellt von WS LANDCAD am 21.09.2022-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/5/4" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/4 http://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/5.4/XPlanung-Operationen.xsd" gml:id="GML_84bd27b7-ad00-497f-85c9-e863e3002e5b">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>562254.000 5938444.000</gml:lowerCorner>
      <gml:upperCorner>562354.000 5938544.000</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_20bdce8c-34a5-42dc-9f63-0ddee6c5641a">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562254.000 5938444.000</gml:lowerCorner>
          <gml:upperCorner>562354.000 5938544.000</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Test_2mm_Insel</xplan:name>
      <xplan:beschreibung>2mm</xplan:beschreibung>
      <xplan:aendert />
      <xplan:wurdeGeaendertVon />
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_91eb273f-6518-41f2-840c-1faab89eab6d">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_0e60e2b4-1b2f-4170-89c6-869a9ebe4984">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">562254.000 5938444.000 562354.000 5938444.000 562354.000 5938544.000 562254.000 5938544.000 562254.000 5938444.000 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
              <gml:interior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">562279.000 5938519.000 562329.000 5938519.000 562329.000 5938469.000 562279.000 5938469.000 562279.000 5938519.000 </gml:posList>
                </gml:LinearRing>
              </gml:interior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:verfahrensMerkmale />
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>02000000</xplan:ags>
          <xplan:gemeindeName>Freie und Hansestadt Hamburg</xplan:gemeindeName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:plangeber />
      <xplan:planArt>1000</xplan:planArt>
      <xplan:veraenderungssperre>false</xplan:veraenderungssperre>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#GML_6a5eaf0b-bf87-4bbf-944d-59bdf60c1be4" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_6a5eaf0b-bf87-4bbf-944d-59bdf60c1be4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562254.000 5938444.000</gml:lowerCorner>
          <gml:upperCorner>562354.000 5938544.000</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:planinhalt xlink:href="#GML_963f8e7d-0e35-4e72-8515-47bc399347e8" />
      <xplan:planinhalt xlink:href="#GML_48d4501f-e4f7-479c-a1d9-b8ed2471335b" />
      <xplan:praesentationsobjekt xlink:href="#GML_513adb97-5df9-48a9-ad2a-d8d1a36fa802" />
      <xplan:praesentationsobjekt xlink:href="#GML_8ffdcaf5-967d-4076-87d6-c6c4dbf7ffe1" />
      <xplan:gehoertZuPlan xlink:href="#GML_20bdce8c-34a5-42dc-9f63-0ddee6c5641a" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_963f8e7d-0e35-4e72-8515-47bc399347e8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562254.000 5938519.000</gml:lowerCorner>
          <gml:upperCorner>562354.000 5938544.000</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_6a5eaf0b-bf87-4bbf-944d-59bdf60c1be4" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_513adb97-5df9-48a9-ad2a-d8d1a36fa802" />
      <xplan:startBedingung />
      <xplan:endeBedingung />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:laermkontingent />
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_716c7cbd-5db7-48fa-b0aa-6a2c7fecb88c">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="5">562254.000 5938544.000 562254.000 5938519.000 562354.000 5938519.000 562354.000 5938544.000 562254.000 5938544.000 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:dachgestaltung />
      <xplan:besondereArtDerBaulNutzung>1700</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_48d4501f-e4f7-479c-a1d9-b8ed2471335b">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562254.000 5938444.000</gml:lowerCorner>
          <gml:upperCorner>562354.000 5938519.000</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_6a5eaf0b-bf87-4bbf-944d-59bdf60c1be4" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_8ffdcaf5-967d-4076-87d6-c6c4dbf7ffe1" />
      <xplan:startBedingung />
      <xplan:endeBedingung />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:laermkontingent />
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_49a34f1e-9949-4bd4-923b-2dea09d4125e">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="9">562329.005 5938469.000 562279.000 5938469.000 562279.000 5938519.000 562254.000 5938519.000 562254.000 5938444.000 562354.000 5938444.000 562354.000 5938519.000 562329.000 5938519.000 562329.005 5938469.000 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:dachgestaltung />
      <xplan:besondereArtDerBaulNutzung>2100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_513adb97-5df9-48a9-ad2a-d8d1a36fa802">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562304.000 5938531.500</gml:lowerCorner>
          <gml:upperCorner>562305.000 5938531.500</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung[0]</xplan:art>
      <xplan:index>0</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#GML_6a5eaf0b-bf87-4bbf-944d-59bdf60c1be4" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_963f8e7d-0e35-4e72-8515-47bc399347e8" />
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_69385cca-59ab-4aa4-ac2c-0a84fe9dc7b3">
          <gml:pos>562304.000 5938531.500</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0.0</xplan:drehwinkel>
      <xplan:skalierung>1.0</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_8ffdcaf5-967d-4076-87d6-c6c4dbf7ffe1">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562304.000 5938456.500</gml:lowerCorner>
          <gml:upperCorner>562305.000 5938457.500</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung[0]</xplan:art>
      <xplan:index>0</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#GML_6a5eaf0b-bf87-4bbf-944d-59bdf60c1be4" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_48d4501f-e4f7-479c-a1d9-b8ed2471335b" />
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_9c2ba74e-5be5-4070-8fa0-9c12dfeaa563">
          <gml:pos>562304.000 5938456.500</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0.00</xplan:drehwinkel>
      <xplan:skalierung>1</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
</xplan:XPlanAuszug>
