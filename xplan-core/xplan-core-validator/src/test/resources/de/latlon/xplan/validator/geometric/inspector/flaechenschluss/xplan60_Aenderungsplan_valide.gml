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

<!--Erstellt von WS LANDCAD am 18.05.2022-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/6/0" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xsi:schemaLocation="http://www.xplanung.de/xplangml/6/0 http://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/6.0/XPlanung-Operationen.xsd" gml:id="GML_56153fd1-590d-4106-a082-00468c1bf50c">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>563014.666 5928972.869</gml:lowerCorner>
      <gml:upperCorner>563567.498 5929359.862</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_9ee7445b-a079-403e-8717-cbc0dd9687ee">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>563014.666 5928972.869</gml:lowerCorner>
          <gml:upperCorner>563567.498 5929359.862</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Original60_test(1Aend)</xplan:name>
      <xplan:beschreibung>test</xplan:beschreibung>       
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_619facf3-c2db-4e75-8a38-c11903dec272">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_3018a8a9-fb00-4cc7-9177-ed3356b22ce8">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">563014.666 5928972.869 563567.498 5928972.869 563567.498 5929359.862 563014.666 5929359.862 563014.666 5928972.869 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
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
      <xplan:rechtsstand>3000</xplan:rechtsstand>     
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#GML_033a8b97-b5f7-4453-99d7-b624fc1b4ef5" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_033a8b97-b5f7-4453-99d7-b624fc1b4ef5">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>563233.311 5929068.039</gml:lowerCorner>
          <gml:upperCorner>563378.909 5929241.670</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_8e84e801-64ca-48e0-9dd3-f5132a9e1be5">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_0094ec99-105d-485b-8d7a-d7858bd7a649">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">563233.311 5929068.039 563378.909 5929068.039 563378.909 5929241.670 563233.311 5929241.670 563233.311 5929068.039 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:geltungsbereich>
      <xplan:planinhalt xlink:href="#GML_1dd3969f-1703-4c63-ab27-88b2bb10e090" />
      <xplan:praesentationsobjekt xlink:href="#GML_a8f3211c-d3f2-4170-bf66-b36198fb0b8d" />
      <xplan:aendertPlanBereich><xplan:XP_VerbundenerPlanBereich><xplan:planName>Original60_test</xplan:planName><xplan:aenderungsArt>1000</xplan:aenderungsArt></xplan:XP_VerbundenerPlanBereich></xplan:aendertPlanBereich>
      <xplan:gehoertZuPlan xlink:href="#GML_9ee7445b-a079-403e-8717-cbc0dd9687ee" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_1dd3969f-1703-4c63-ab27-88b2bb10e090">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>563233.311 5929068.039</gml:lowerCorner>
          <gml:upperCorner>563378.909 5929241.670</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_033a8b97-b5f7-4453-99d7-b624fc1b4ef5" />
      <xplan:wirdDargestelltDurch xlink:href="#GML_a8f3211c-d3f2-4170-bf66-b36198fb0b8d" />
      <xplan:startBedingung />
      <xplan:endeBedingung />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_a4069ab8-6ccc-4cff-bfdd-31b424bda705">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="5">563233.311 5929068.039 563378.909 5929068.039 563378.909 5929241.670 563233.311 5929241.670 563233.311 5929068.039 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:dachgestaltung />
      <xplan:besondereArtDerBaulNutzung>1500</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:XP_PPO gml:id="GML_a8f3211c-d3f2-4170-bf66-b36198fb0b8d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>563306.110 5929154.854</gml:lowerCorner>
          <gml:upperCorner>563307.110 5929154.854</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:art>besondereArtDerBaulNutzung</xplan:art>     
      <xplan:gehoertZuBereich xlink:href="#GML_033a8b97-b5f7-4453-99d7-b624fc1b4ef5" />
      <xplan:dientZurDarstellungVon xlink:href="#GML_1dd3969f-1703-4c63-ab27-88b2bb10e090" />
      <xplan:position>
        <gml:Point srsName="EPSG:25832" gml:id="GML_5537fecb-034e-46ea-970b-4b73f2eb8031">
          <gml:pos>563306.110 5929154.854</gml:pos>
        </gml:Point>
      </xplan:position>
      <xplan:drehwinkel uom="grad">0.0</xplan:drehwinkel>
      <xplan:skalierung>1.0</xplan:skalierung>
    </xplan:XP_PPO>
  </gml:featureMember>
</xplan:XPlanAuszug>
