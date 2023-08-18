<?xml version="1.0" encoding="utf-8"?>
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

<xplan:XPlanAuszug xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="Gml_6EC2A805-FB62-48AB-B106-1AF1CC1BA13C" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/2 http://www.xplanungwiki.de/upload/XPlanGML/5.2/Schema/XPlanung-Operationen.xsd" xmlns:xplan="http://www.xplanung.de/xplangml/5/2">
  <!-- Fehler 2.2.3.1: Schnittpunkt mit dem Umring des Geltungsbereiches (574547.88296709,5947355.4007816175) -->
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>574482.1388 5947250.1897</gml:lowerCorner>
      <gml:upperCorner>574620.3839 5947356.0232</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="Gml_C30DA3A3-E655-4ECC-9F3C-FB8745C3EC01">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574482.1388 5947250.1897</gml:lowerCorner>
          <gml:upperCorner>574620.3839 5947356.0232</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>BPlan002_5-2_Schnittpunkt_Geltungsbereich</xplan:name>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon gml:id="Gml_BFFEBC5F-F7A4-4C28-8AB7-CE6C6DF00177" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574615.5888 5947300.5841 574616.9994 5947307.1943 574620.3839 5947354.7003 
574483.4617 5947356.0232 574482.1388 5947250.8511 574611.1234 5947250.1897 
574615.5888 5947300.5841 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>02000000</xplan:ags>
          <xplan:gemeindeName>Freie und Hansestadt Hamburg</xplan:gemeindeName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:rechtsstand>3000</xplan:rechtsstand>
      <xplan:bereich xlink:href="#Gml_0F60DB02-659D-4A76-B20F-D696982CE390" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="Gml_0F60DB02-659D-4A76-B20F-D696982CE390">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574482.1388 5947250.1897</gml:lowerCorner>
          <gml:upperCorner>574620.3839 5947356.0232</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:Polygon gml:id="Gml_91D06BA4-4BCF-45B6-81B5-EB0F53C2090B" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574615.5888 5947300.5841 574616.9994 5947307.1943 574620.3839 5947354.7003 
574483.4617 5947356.0232 574482.1388 5947250.8511 574611.1234 5947250.1897 
574615.5888 5947300.5841 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:geltungsbereich>
      <xplan:planinhalt xlink:href="#Gml_64A09EB6-CA4A-491A-BE89-7CEFD3344307" />
      <xplan:planinhalt xlink:href="#Gml_28A3381F-A6F2-45D4-A668-CD2F3BA89F2F" />
      <xplan:gehoertZuPlan xlink:href="#Gml_C30DA3A3-E655-4ECC-9F3C-FB8745C3EC01" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:SO_Denkmalschutzrecht gml:id="Gml_64A09EB6-CA4A-491A-BE89-7CEFD3344307">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574547.8759 5947281.7313</gml:lowerCorner>
          <gml:upperCorner>574620.3839 5947356.7624</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_0F60DB02-659D-4A76-B20F-D696982CE390" />
      <xplan:rechtscharakter>2000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_0E0AD094-F9B3-43A5-9BD4-38BB14DBCECA" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574615.5888 5947300.5841 574620.3839 5947354.7003 574547.8759 5947356.7624 
574548.2608 5947282.6036 574613.9183 5947281.7313 574615.5888 5947300.5841 
</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:artDerFestlegung>1100</xplan:artDerFestlegung>
    </xplan:SO_Denkmalschutzrecht>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="Gml_28A3381F-A6F2-45D4-A668-CD2F3BA89F2F">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574482.1388 5947250.1897</gml:lowerCorner>
          <gml:upperCorner>574620.3839 5947356.0232</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_0F60DB02-659D-4A76-B20F-D696982CE390" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_225D1268-C779-4550-91B1-F7044FDB25E7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574615.5888 5947300.5841 574616.9994 5947307.1943 574620.3839 5947354.7003 
574483.4617 5947356.0232 574482.1388 5947250.8511 574611.1234 5947250.1897 
574615.5888 5947300.5841 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:allgArtDerBaulNutzung>1000</xplan:allgArtDerBaulNutzung>
      <xplan:besondereArtDerBaulNutzung>1100</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
</xplan:XPlanAuszug>
