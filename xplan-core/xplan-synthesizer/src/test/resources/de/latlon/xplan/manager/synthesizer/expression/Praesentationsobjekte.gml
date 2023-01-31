<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!--
  #%L
  xplan-synthesizer - XPlan Manager Synthesizer Komponente
  %%
  Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
  %%
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
  #L%
  -->

    <!--Erstellt von WS LANDCAD am 16.01.2020-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/5/1" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" gml:id="GML_3698c5cf-f95f-4509-ada6-85354d1f0eef">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>562764.018 5940325.295</gml:lowerCorner>
      <gml:upperCorner>563255.004 5940506.221</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="BP_PLAN">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562764.018 5940325.295</gml:lowerCorner>
          <gml:upperCorner>563255.004 5940506.221</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Prasentationsobjekte</xplan:name>
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_02ba4353-ca8a-43d3-95d2-cbc1bc716f58">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_98462f59-e57f-483f-a5ac-9597a548e8e2">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="42">563250.869 5940348.172 563251.305 5940370.167 563255.004 5940421.276 563126.503 5940443.287 563115.213 5940445.106 563113.586 5940445.368 563032.157 5940458.488 563017.230 5940460.893 562987.089 5940465.749 562983.980 5940466.250 562962.795 5940471.861 562834.347 5940505.516 562831.298 5940505.988 562829.794 5940506.221 562825.855 5940496.053 562818.356 5940476.698 562814.084 5940474.573 562783.147 5940459.178 562773.537 5940455.739 562764.018 5940453.825 562770.663 5940412.454 562772.858 5940398.558 562778.599 5940362.218 562783.421 5940362.402 562893.069 5940366.586 562902.117 5940366.929 562934.112 5940368.141 562967.522 5940369.420 562973.246 5940369.450 562988.990 5940369.421 563040.322 5940367.165 563061.241 5940365.150 563091.204 5940361.518 563110.017 5940358.512 563137.608 5940353.683 563152.071 5940350.830 563177.510 5940345.332 563206.992 5940337.638 563217.122 5940336.612 563222.043 5940334.583 563251.841 5940325.295 563250.869 5940348.172 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>02000000</xplan:ags>
          <xplan:gemeindeName>Freie und Hansestadt Hamburg</xplan:gemeindeName>
          <xplan:ortsteilName>102</xplan:ortsteilName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:rechtsstand>3000</xplan:rechtsstand>
      <xplan:veraenderungssperre>false</xplan:veraenderungssperre>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#BP_BEREICH" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="BP_BEREICH">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562764.018 5940358.512</gml:lowerCorner>
          <gml:upperCorner>563255.004 5940445.106</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:planinhalt xlink:href="#BP_BAUGEBTEILFLAECHE" />
      <xplan:praesentationsobjekt xlink:href="#XP_PTO_EMPTY" />
      <xplan:praesentationsobjekt xlink:href="#XP_PTO" />
      <xplan:gehoertZuPlan xlink:href="#BP_PLAN" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="BP_BAUGEBTEILFLAECHE">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>563110.017 5940325.295</gml:lowerCorner>
          <gml:upperCorner>563255.004 5940445.106</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#BP_BEREICH" />
      <xplan:wirdDargestelltDurch xlink:href="#XP_PPO" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_62b7ddf8-0ccd-463d-ba5f-1af9ad2f2503">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="14">563250.869 5940348.172 563251.305 5940370.167 563255.004 5940421.276 563126.503 5940443.287 563115.213 5940445.106 563110.017 5940358.512 563137.608 5940353.683 563152.071 5940350.830 563177.510 5940345.332 563206.992 5940337.638 563217.122 5940336.612 563222.043 5940334.583 563251.841 5940325.295 563250.869 5940348.172 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:Z>3</xplan:Z>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
      <xplan:bauweise>1000</xplan:bauweise>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_GruenFlaeche gml:id="BP_GRUENFLAECHE">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562764.018 5940358.512</gml:lowerCorner>
          <gml:upperCorner>563115.213 5940506.221</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#BP_BEREICH" />
      <xplan:wirdDargestelltDurch xlink:href="#XP_PTO_EMPTY" />
      <xplan:wirdDargestelltDurch xlink:href="#XP_PTO" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_dcbab867-07c7-4827-8b10-c8001f4f4cc4">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="31">563115.213 5940445.106 563113.586 5940445.368 563032.157 5940458.488 563017.230 5940460.893 562987.089 5940465.749 562983.980 5940466.250 562962.795 5940471.861 562834.347 5940505.516 562831.298 5940505.988 562829.794 5940506.221 562825.855 5940496.053 562818.356 5940476.698 562814.084 5940474.573 562783.147 5940459.178 562773.537 5940455.739 562764.018 5940453.825 562770.663 5940412.454 562772.858 5940398.558 562778.599 5940362.218 562783.421 5940362.402 562893.069 5940366.586 562902.117 5940366.929 562934.112 5940368.141 562967.522 5940369.420 562973.246 5940369.450 562988.990 5940369.421 563040.322 5940367.165 563061.241 5940365.150 563091.204 5940361.518 563110.017 5940358.512 563115.213 5940445.106 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:zweckbestimmung>1000</xplan:zweckbestimmung>
      <xplan:zweckbestimmung>1600</xplan:zweckbestimmung>
      <xplan:nutzungsform>2000</xplan:nutzungsform>
    </xplan:BP_GruenFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PTO gml:id="XP_PTO">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562916.780 5940397.998</gml:lowerCorner>
          <gml:upperCorner>562917.780 5940398.998</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      <xplan:index>1</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#BP_BEREICH" />
      <xplan:dientZurDarstellungVon xlink:href="#BP_GRUENFLAECHE" />
      <xplan:schriftinhalt>Spielplatz</xplan:schriftinhalt>
      <xplan:skalierung>3.82535</xplan:skalierung>
      <xplan:horizontaleAusrichtung>linksbündig</xplan:horizontaleAusrichtung>
      <xplan:vertikaleAusrichtung>Oben</xplan:vertikaleAusrichtung>
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_76522e44-0638-4981-ac32-eaa48ab4329f">
          <gml:pos>562916.780 5940397.998</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">72.41</xplan:drehwinkel>
    </xplan:XP_PTO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PTO gml:id="XP_PTO_EMPTY">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>562916.780 5940397.998</gml:lowerCorner>
          <gml:upperCorner>562917.780 5940398.998</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      <xplan:index>1</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#BP_BEREICH" />
      <xplan:dientZurDarstellungVon xlink:href="#BP_GRUENFLAECHE" />
      <xplan:schriftinhalt>Spielplatz</xplan:schriftinhalt>
      <xplan:skalierung>3.82535</xplan:skalierung>
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_76522e44-0638-4981-ac32-eaa48ab4329f">
          <gml:pos>562916.780 5940397.998</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">72.41</xplan:drehwinkel>
    </xplan:XP_PTO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="XP_PPO">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>563145.588 5940391.588</gml:lowerCorner>
          <gml:upperCorner>563146.588 5940392.588</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>Z</xplan:art>
      <xplan:index>0</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#BP_BEREICH" />
      <xplan:dientZurDarstellungVon xlink:href="#BP_BAUGEBTEILFLAECHE" />
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_c6947dde-717f-47bc-a242-56a91a0f80a3">
          <gml:pos>563145.588 5940391.588</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0.00</xplan:drehwinkel>
      <xplan:skalierung>4.92511</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
</xplan:XPlanAuszug>
