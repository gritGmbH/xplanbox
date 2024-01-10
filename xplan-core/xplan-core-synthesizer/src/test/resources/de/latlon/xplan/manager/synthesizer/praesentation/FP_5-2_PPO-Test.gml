<?xml version="1.0" encoding="utf-8"?>
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

<xplan:XPlanAuszug xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="Gml_F03FC75F-BF0D-4FDD-B5ED-D62170D4A6F8" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/2 http://www.xplanungwiki.de/upload/XPlanGML/5.2/Schema/XPlanung-Operationen.xsd" xmlns:xplan="http://www.xplanung.de/xplangml/5/2">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>558358.6559 5934058.1968</gml:lowerCorner>
      <gml:upperCorner>560492.2602 5935921.632</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:FP_Plan gml:id="Gml_B6C70001-17AB-48A2-A9E8-9AC87B4F5F98">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>558358.6559 5934058.1968</gml:lowerCorner>
          <gml:upperCorner>560492.2602 5935921.632</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>FP_5-2_PPO-Test</xplan:name>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon gml:id="Gml_9B1ACEFB-1A75-49AA-9E63-6B2FA00D6FDB" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">558358.6559 5935875.7412 558398.4925 5934058.1968 560492.2602 5934104.0876 
560452.4236 5935921.632 558358.6559 5935875.7412 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>00000000</xplan:ags>
          <xplan:gemeindeName>Test-Gemeinde</xplan:gemeindeName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:rechtsstand>4000</xplan:rechtsstand>
      <xplan:bereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
    </xplan:FP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:FP_Bereich gml:id="Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>558358.6559 5934058.1968</gml:lowerCorner>
          <gml:upperCorner>560492.2602 5935921.632</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:Polygon gml:id="Gml_E0EC4613-6E78-4BD3-A60D-622DAFAFC3D7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">558358.6559 5935875.7412 558398.4925 5934058.1968 560492.2602 5934104.0876 
560452.4236 5935921.632 558358.6559 5935875.7412 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:geltungsbereich>
      <xplan:planinhalt xlink:href="#Gml_23AAE82B-80D3-40BF-B930-58F86738399B" />
      <xplan:planinhalt xlink:href="#Gml_55145C9A-B4CC-4BCB-B1BD-F7299E259F35" />
      <xplan:praesentationsobjekt xlink:href="#Gml_3B7A5EC7-E969-4937-841E-CA25E8C68B2A" />
      <xplan:praesentationsobjekt xlink:href="#Gml_F42B47C8-F232-4416-9682-CBC93C0A9C74" />
      <xplan:praesentationsobjekt xlink:href="#Gml_E6E0D89A-FD9B-408F-9750-3FFC06FAD80E" />
      <xplan:praesentationsobjekt xlink:href="#Gml_48C4665E-9BCA-4195-80EE-9B716CF71801" />
      <xplan:gehoertZuPlan xlink:href="#Gml_B6C70001-17AB-48A2-A9E8-9AC87B4F5F98" />
    </xplan:FP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:FP_Gemeinbedarf gml:id="Gml_23AAE82B-80D3-40BF-B930-58F86738399B">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>558358.6559 5934058.1968</gml:lowerCorner>
          <gml:upperCorner>559396.3848 5935896.3951</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:rechtsstand>1000</xplan:rechtsstand>
      <xplan:gehoertZuBereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_E6E0D89A-FD9B-408F-9750-3FFC06FAD80E" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_48C4665E-9BCA-4195-80EE-9B716CF71801" />
      <xplan:rechtscharakter>9998</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_DAFB3CB3-3065-4BD3-9B9F-7CA7740223E7" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">558358.6559 5935875.7412 558398.4925 5934058.1968 559396.3848 5934080.0684 
559300.9905 5935896.3951 558358.6559 5935875.7412 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:zweckbestimmung>12000</xplan:zweckbestimmung>
      <xplan:zweckbestimmung>18000</xplan:zweckbestimmung>
    </xplan:FP_Gemeinbedarf>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_E6E0D89A-FD9B-408F-9750-3FFC06FAD80E">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>558800.1406 5935398.9082</gml:lowerCorner>
          <gml:upperCorner>558800.1406 5935398.9082</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      <xplan:index>0</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
      <xplan:dientZurDarstellungVon xlink:href="#Gml_23AAE82B-80D3-40BF-B930-58F86738399B" />
      <xplan:position>
        <gml:Point gml:id="Gml_9E755DA3-05F1-4442-8F2A-DD1C6967AB33" srsName="EPSG:25832">
          <gml:pos>558800.1406 5935398.9082</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_48C4665E-9BCA-4195-80EE-9B716CF71801">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>558863.6407 5934631.615</gml:lowerCorner>
          <gml:upperCorner>558863.6407 5934631.615</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      <xplan:index>1</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
      <xplan:dientZurDarstellungVon xlink:href="#Gml_23AAE82B-80D3-40BF-B930-58F86738399B" />
      <xplan:position>
        <gml:Point gml:id="Gml_3C9489D7-BF35-4D6D-B330-E24BA113265B" srsName="EPSG:25832">
          <gml:pos>558863.6407 5934631.615</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:FP_Gruen gml:id="Gml_55145C9A-B4CC-4BCB-B1BD-F7299E259F35">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>559300.9905 5934080.0684</gml:lowerCorner>
          <gml:upperCorner>560492.2602 5935921.632</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_3B7A5EC7-E969-4937-841E-CA25E8C68B2A" />
      <xplan:wirdDargestelltDurch xlink:href="#Gml_F42B47C8-F232-4416-9682-CBC93C0A9C74" />
      <xplan:rechtscharakter>9998</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_089B4D02-2E4E-4244-8D84-DCAB652BDDB6" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">559300.9905 5935896.3951 559396.3848 5934080.0684 560492.2602 5934104.0876 
560452.4236 5935921.632 559300.9905 5935896.3951 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:zweckbestimmung>1400</xplan:zweckbestimmung>
      <xplan:zweckbestimmung>1200</xplan:zweckbestimmung>
    </xplan:FP_Gruen>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_3B7A5EC7-E969-4937-841E-CA25E8C68B2A">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>559724.332 5935478.0187</gml:lowerCorner>
          <gml:upperCorner>559724.332 5935478.0187</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      <xplan:index>0</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
      <xplan:dientZurDarstellungVon xlink:href="#Gml_55145C9A-B4CC-4BCB-B1BD-F7299E259F35" />
      <xplan:position>
        <gml:Point gml:id="Gml_E771B0C4-27C4-42B3-BB46-D6224C3ADB47" srsName="EPSG:25832">
          <gml:pos>559724.332 5935478.0187</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="Gml_F42B47C8-F232-4416-9682-CBC93C0A9C74">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>559939.9679 5934633.9962</gml:lowerCorner>
          <gml:upperCorner>559939.9679 5934633.9962</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>zweckbestimmung</xplan:art>
      <xplan:index>1</xplan:index>
      <xplan:gehoertZuBereich xlink:href="#Gml_5AE01AA7-88DB-4FD0-A3C1-43FA10EA808B" />
      <xplan:dientZurDarstellungVon xlink:href="#Gml_55145C9A-B4CC-4BCB-B1BD-F7299E259F35" />
      <xplan:position>
        <gml:Point gml:id="Gml_4C88E290-0000-4DDE-A306-E85E08DE3C6A" srsName="EPSG:25832">
          <gml:pos>559939.9679 5934633.9962</gml:pos>
        </gml:Point>
      </xplan:position>
    </xplan:XP_PPO>
  </gml:featureMember>
</xplan:XPlanAuszug>
