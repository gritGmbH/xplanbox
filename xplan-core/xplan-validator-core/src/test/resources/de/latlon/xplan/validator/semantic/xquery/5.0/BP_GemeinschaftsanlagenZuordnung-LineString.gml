<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!--
  #%L
  xplan-validator-core - XPlan Validator Core Komponente
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

<!--Erzeugt mit KIT (www.kit.edu) GML-Toolbox, Erstellungsdatum: 12/07/16-->
<xplan:XPlanAuszug xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.xplanung.de/xplangml/5/0 ../../Schema/XPlanung-Operationen.xsd"
                   xmlns:xplan="http://www.xplanung.de/xplangml/5/0"
                   xmlns:xlink="http://www.w3.org/1999/xlink"
                   xmlns:gml="http://www.opengis.net/gml/3.2"
                   gml:id="GML_40adb0a5-8ba6-478e-8384-c1939d2711c7">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
      <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
      <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_GemeinschaftsanlagenZuordnung gml:id="GML_1265b858-4bfa-4694-94dd-f3b3cbd9ee03">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:31467">
          <gml:lowerCorner>3954683.088 5499947.56</gml:lowerCorner>
          <gml:upperCorner>3954716.126 5499966.522</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="GML_e098a71d-b6d5-4adc-b96d-83a194dcacac">
          <gml:posList srsDimension="2" count="11">3954715.608 5499966.522 3954716.126 5499951.526
            3954710.115 5499951.17 3954701.227 5499950.272 3954692.491 5499948.856 3954686.404 5499947.56
            3954683.088 5499962.19 3954689.727 5499963.604 3954699.271 5499965.15 3954708.916 5499966.125
            3954715.608 5499966.522
          </gml:posList>
        </gml:LineString>
      </xplan:position>
    </xplan:BP_GemeinschaftsanlagenZuordnung>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:31467">
          <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
          <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:name>defaultBereich</xplan:name>
      <xplan:versionBauNVODatum>2002-11-03</xplan:versionBauNVODatum>
      <xplan:versionBauNVOText>Verordnung über die bauliche Nutzung der Grundstücke
        (Baunutzungsverordnung - BauNVO)
      </xplan:versionBauNVOText>
      <xplan:versionBauGBDatum>2004-09-23</xplan:versionBauGBDatum>
      <xplan:versionBauGBText>"Baugesetzbuch in der Fassung der Bekanntmachung vom 23. September 2004
      </xplan:versionBauGBText>
      <xplan:gehoertZuPlan xlink:href="#GML_4ad825d0-5819-4126-be9d-f1e0b885374c"/>
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_4ad825d0-5819-4126-be9d-f1e0b885374c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:31467">
          <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
          <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>BPlan Demo-Gemeinde</xplan:name>
      <xplan:technHerstellDatum>2006-06-22</xplan:technHerstellDatum>
      <xplan:wurdeGeaendertVon>
        <xplan:XP_VerbundenerPlan>
          <xplan:planName>DemoPlanAenderung_1</xplan:planName>
          <xplan:rechtscharakter>1100</xplan:rechtscharakter>
        </xplan:XP_VerbundenerPlan>
      </xplan:wurdeGeaendertVon>
      <xplan:bezugshoehe uom="m">110</xplan:bezugshoehe>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon srsName="EPSG:31467" gml:id="GML_125781e4-36ae-4d79-af38-44880d485b8b">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="35">3954678.828 5499967.417 3954691.042 5499913.525
                3954675.148 5499911.792 3954659.071 5499908.004 3954639.089 5499903.299 3954633.369
                5499901.13 3954637.888 5499886.14 3954639.179 5499881.862 3954672.269 5499887.85
                3954675.333
                5499897.894 3954702.538 5499893.471 3954725.947 5499884.456 3954735.247 5499880.877
                3954748.06 5499870.089 3954752.164 5499865.665 3954754.756 5499862.867 3954757.256
                5499860.173 3954761.394 5499869.122 3954764.285 5499878.571 3954767.624 5499897.422
                3954767.609 5499918.603 3954767.608 5499923.714 3954791.49 5499929.16 3954791.478
                5499946.05
                3954791.458 5499967.086 3954775.589 5499967.563 3954755.146 5499970.567 3954746.875
                5499971.71 3954738.584 5499972.437 3954728.477 5499972.787 3954718.396 5499972.698
                3954708.436 5499972.107 3954698.489 5499971.102 3954688.621 5499969.503 3954678.828
                5499967.417
              </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:externeReferenz>
        <xplan:XP_SpezExterneReferenz>
          <xplan:art>Dokument</xplan:art>
          <xplan:referenzURL>http://www.Demogemeinde.de/BPlaene/DemoplanBegruendung.pdf</xplan:referenzURL>
          <xplan:referenzMimeType
                  codeSpace="http://www.xplanungwiki.de/upload/XPlanGML/5.0.BetaV2/Schema/Codelists/xplan_XP_MimeTypes.xml"
          >application/pdf
          </xplan:referenzMimeType>
          <xplan:typ>1000</xplan:typ>
        </xplan:XP_SpezExterneReferenz>
      </xplan:externeReferenz>
      <xplan:externeReferenz>
        <xplan:XP_SpezExterneReferenz>
          <xplan:art>Dokument</xplan:art>
          <xplan:referenzURL>http://www.Demogemeinde.de/BPlaene/Demoplan.pdf</xplan:referenzURL>
          <xplan:referenzMimeType
                  codeSpace="http://www.xplanungwiki.de/upload/XPlanGML/5.0.BetaV2/Schema/Codelists/xplan_XP_MimeTypes.xml"
          >application/pdf
          </xplan:referenzMimeType>
          <xplan:typ>1030</xplan:typ>
        </xplan:XP_SpezExterneReferenz>
      </xplan:externeReferenz>
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>1234567</xplan:ags>
          <xplan:gemeindeName>Demo-Gemeinde</xplan:gemeindeName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:verfahren>2000</xplan:verfahren>
      <xplan:rechtsstand>4000</xplan:rechtsstand>
      <xplan:inkrafttretensDatum>2006-09-01</xplan:inkrafttretensDatum>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:bereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
    </xplan:BP_Plan>
  </gml:featureMember>
</xplan:XPlanAuszug>
