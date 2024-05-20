<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!--Erstellt von WS LANDCAD am 28.11.2022-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/5/4" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/4 http://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/5.4/XPlanung-Operationen.xsd" gml:id="GML_2679a658-4b46-463e-8bb2-3ffb14f2e2ef">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25833">
      <gml:lowerCorner>679618.895 5405410.431</gml:lowerCorner>
      <gml:upperCorner>679646.336 5405437.698</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_4a650c7c-3d4a-4c4b-b20e-7ead9baf5b9d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>679618.895 5405410.431</gml:lowerCorner>
          <gml:upperCorner>679646.336 5405437.698</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>asdf</xplan:name>
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25833" gml:id="GML_fc6fab5f-9357-4b23-8a88-fb4ff0138c65">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25833" gml:id="GML_c5f596c3-eb42-4fe1-a929-986d1d1d274e">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">679641.678 5405410.431 679646.336 5405432.915 679619.911 5405437.698 679618.895 5405413.743 679641.678 5405410.431 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>asdf</xplan:ags>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:veraenderungssperre>false</xplan:veraenderungssperre>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#GML_1be21f8d-cbbf-4371-9795-9ddd3aa9f8de" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_1be21f8d-cbbf-4371-9795-9ddd3aa9f8de">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>679618.895 5405410.431</gml:lowerCorner>
          <gml:upperCorner>679646.336 5405437.698</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:gehoertZuPlan xlink:href="#GML_4a650c7c-3d4a-4c4b-b20e-7ead9baf5b9d" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_0d7d2c99-71aa-4420-a6e4-76f0a2f3c6f9">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>679620.953 5405414.190</gml:lowerCorner>
          <gml:upperCorner>679640.563 5405433.130</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_1be21f8d-cbbf-4371-9795-9ddd3aa9f8de" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25833" gml:id="GML_0877f2d1-afd8-48ca-b894-2d6cd80c90d0">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:Curve srsName="EPSG:25833" gml:id="GML_3d2ff3ec-7e7a-4723-8a56-ba0beb76d466">
                  <gml:segments>
                    <gml:ArcString interpolation="circularArc3Points">
                      <gml:posList srsDimension="2" count="3">679620.953 5405421.162 679623.798 5405416.017 679629.391 5405414.206 </gml:posList>
                    </gml:ArcString>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="3">679629.391 5405414.206 679634.283 5405414.515 679634.056 5405418.113 </gml:posList>
                    </gml:LineStringSegment>
                    <gml:ArcString interpolation="circularArc3Points">
                      <gml:posList srsDimension="2" count="3">679634.056 5405418.113 679635.504 5405426.404 679640.563 5405433.130 </gml:posList>
                    </gml:ArcString>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="8">679640.563 5405433.130 679633.620 5405427.400 679625.500 5405420.330 679624.960 5405419.820 679623.600 5405419.370 679622.190 5405419.570 679621.160 5405420.550 679620.953 5405421.162 </gml:posList>
                    </gml:LineStringSegment>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:besondereArtDerBaulNutzung>1000</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
</xplan:XPlanAuszug>
