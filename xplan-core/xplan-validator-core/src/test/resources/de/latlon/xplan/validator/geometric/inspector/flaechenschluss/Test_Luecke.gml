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

<xplan:XPlanAuszug xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="Gml_CF2C4042-E8E5-4001-A001-6B1A3DD4F11A" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/2 http://www.xplanungwiki.de/upload/XPlanGML/5.2/Schema/XPlanung-Operationen.xsd" xmlns:xplan="http://www.xplanung.de/xplangml/5/2">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>572684.3684 5938133.1819</gml:lowerCorner>
      <gml:upperCorner>572773.4053 5938250.6555</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="Gml_A525E7AF-9675-4343-9623-54AD15D6B580">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572684.3684 5938133.1819</gml:lowerCorner>
          <gml:upperCorner>572773.4053 5938250.6555</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Test Lücke</xplan:name>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon gml:id="Gml_FF0A7E6E-F033-48C5-B7D9-871930859989" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">572684.3684 5938250.2322 572684.9284 5938133.1819 572773.4053 5938133.6053 
572772.8452 5938250.6555 572684.3684 5938250.2322 </gml:posList>
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
      <xplan:bereich xlink:href="#Gml_3B197852-4DA2-405F-98D9-73A2EE20F22D" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="Gml_3B197852-4DA2-405F-98D9-73A2EE20F22D">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572684.3684 5938133.1819</gml:lowerCorner>
          <gml:upperCorner>572773.4053 5938250.6555</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:Polygon gml:id="Gml_382D844F-975E-4122-A965-C39E95F38ED8" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">572684.3684 5938250.2322 572684.9284 5938133.1819 572773.4053 5938133.6053 
572772.8452 5938250.6555 572684.3684 5938250.2322 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:geltungsbereich>
      <xplan:planinhalt xlink:href="#Gml_EDBAF2FA-8AA6-434D-A8A5-99D576AAD6B7" />
      <xplan:planinhalt xlink:href="#Gml_49A97525-60B0-460F-9A54-20E6B790E517" />
      <xplan:gehoertZuPlan xlink:href="#Gml_A525E7AF-9675-4343-9623-54AD15D6B580" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_GruenFlaeche gml:id="Gml_EDBAF2FA-8AA6-434D-A8A5-99D576AAD6B7">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572684.3684 5938185.0453</gml:lowerCorner>
          <gml:upperCorner>572773.1592 5938250.6555</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_3B197852-4DA2-405F-98D9-73A2EE20F22D" />
      <xplan:rechtscharakter>9998</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_C35EE6F1-2F07-40D3-9A01-DD354058E32C" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">572684.3684 5938250.2322 572684.6742 5938186.3092 572773.1592 5938185.0453 
572772.8452 5938250.6555 572684.3684 5938250.2322 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:zweckbestimmung>1000</xplan:zweckbestimmung>
    </xplan:BP_GruenFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_WaldFlaeche gml:id="Gml_49A97525-60B0-460F-9A54-20E6B790E517">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572684.6742 5938133.1819</gml:lowerCorner>
          <gml:upperCorner>572773.4053 5938186.3092</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_3B197852-4DA2-405F-98D9-73A2EE20F22D" />
      <xplan:rechtscharakter>9998</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_F63DE04E-4BF8-4CC4-86E4-AC8217B9CFCC" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">572684.6742 5938186.3092 572684.9284 5938133.1819 572773.4053 5938133.6053 
572770.0637 5938185.0895 572684.6742 5938186.3092 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
    </xplan:BP_WaldFlaeche>
  </gml:featureMember>
</xplan:XPlanAuszug>
