<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!--Erstellt von WS LANDCAD am 05.07.2022-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/5/4" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/4 http://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/5.4/XPlanung-Operationen.xsd" gml:id="GML_8d1ed935-372e-440a-b9b4-5036c95cf5f6">
<!-- Testplan, der folgende geometrische Besonderheiten aufweist:
* zusätzlicher Stützpunkt in Baugebietsteilfläche überlappt die Insel des Geltungsbereiches um 0,8mm (560555.9335 5938718.6172)
* zusätzlicher Stützpunkt in Baugebietsteilfläche führt zur Lücke an Insel des Geltungsbereiches um 0,8mm (560490.0487 5938719.1679)

-->
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>560382.732 5938623.514</gml:lowerCorner>
      <gml:upperCorner>560650.347 5938819.075</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_4b1ba2c2-c5e7-4996-9d88-8013aa6d1dcd">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>560382.732 5938623.514</gml:lowerCorner>
          <gml:upperCorner>560650.347 5938819.075</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>BPlan0001_5_4_Toleranz_Geltung</xplan:name>
      <xplan:aendert />
      <xplan:wurdeGeaendertVon />
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_496573c7-056c-44ff-b7fd-e35536794978">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_0ee81fc3-9f5d-419b-9338-5d25c904d18d">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">560382.732 5938623.514 560650.347 5938623.514 560650.347 5938819.075 560382.732 5938819.075 560382.732 5938623.514 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
              <gml:interior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">560490.049 5938749.135 560555.934 5938749.135 560555.934 5938692.775 560490.049 5938692.775 560490.049 5938749.135 </gml:posList>
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
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:rechtsstand>3000</xplan:rechtsstand>
      <xplan:veraenderungssperre>false</xplan:veraenderungssperre>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#GML_61be76f6-08ae-4c27-b109-b3d28a8476fc" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_61be76f6-08ae-4c27-b109-b3d28a8476fc">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>560382.732 5938623.514</gml:lowerCorner>
          <gml:upperCorner>560650.347 5938819.075</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:planinhalt xlink:href="#GML_d55f4e9f-b1ca-4bc9-9a5e-674e79322427" />
      <xplan:praesentationsobjekt xlink:href="#GML_7ae9b024-68b4-4f82-8d02-1bbd7c8b4dbe" />
      <xplan:gehoertZuPlan xlink:href="#GML_4b1ba2c2-c5e7-4996-9d88-8013aa6d1dcd" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_d55f4e9f-b1ca-4bc9-9a5e-674e79322427">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>560382.732 5938623.514</gml:lowerCorner>
          <gml:upperCorner>560650.347 5938819.075</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_61be76f6-08ae-4c27-b109-b3d28a8476fc" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_7ae9b024-68b4-4f82-8d02-1bbd7c8b4dbe" />
      <xplan:startBedingung />
      <xplan:endeBedingung />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_29cdbe8b-6a34-4174-82e9-19abeed7dff6">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="5">560650.347 5938819.075 560382.732 5938819.075 560382.732 5938623.514 560650.347 5938623.514 560650.347 5938819.075 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
          <gml:interior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="7">560490.049 5938749.135 560555.934 5938749.135 560555.9335 5938718.6172 560555.934 5938692.775 560490.049 5938692.775 560490.0487 5938719.1679 560490.049 5938749.135 </gml:posList>
            </gml:LinearRing>
          </gml:interior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:dachgestaltung />
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_7ae9b024-68b4-4f82-8d02-1bbd7c8b4dbe">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>560436.255 5938677.037</gml:lowerCorner>
          <gml:upperCorner>560437.255 5938677.037</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung[0]</xplan:art>
      <xplan:index>0</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#GML_61be76f6-08ae-4c27-b109-b3d28a8476fc" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_d55f4e9f-b1ca-4bc9-9a5e-674e79322427" />
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_14c4299c-a5c8-498d-a342-5b33217c8cdb">
          <gml:pos>560436.255 5938677.037</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0.0</xplan:drehwinkel>
      <xplan:skalierung>1.0</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
</xplan:XPlanAuszug>
