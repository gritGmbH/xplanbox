<?xml version="1.0" encoding="utf-8"?>
<xplan:XPlanAuszug xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="Gml_52054FC7-92CC-41DA-BBDF-092861FCA8C5" xsi:schemaLocation="http://www.xplanung.de/xplangml/6/0 http://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/6.0/XPlanung-Operationen.xsd" xmlns:xplan="http://www.xplanung.de/xplangml/6/0">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>557094.5259 5936699.1238</gml:lowerCorner>
      <gml:upperCorner>557245.9037 5936833.7776</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="Gml_CD6556C4-8BDB-43DA-821A-66FAE8B17BBA">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>557094.5259 5936699.1238</gml:lowerCorner>
          <gml:upperCorner>557245.9037 5936833.7776</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>BPlan_6-0_Linie_ausserhalb</xplan:name>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon gml:id="Gml_64D79F09-8C96-4FB4-A073-8FD2130168BA" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">557096.392 5936828.69 557094.5259 5936699.1238 557245.9037 5936699.9855 
557243.4354 5936833.7776 557096.392 5936828.69 </gml:posList>
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
      <xplan:rechtsstand>3000</xplan:rechtsstand>
      <xplan:bereich xlink:href="#Gml_432F3BD2-639E-4A0F-826E-53D45A7209C6" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="Gml_432F3BD2-639E-4A0F-826E-53D45A7209C6">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>557094.5259 5936699.1238</gml:lowerCorner>
          <gml:upperCorner>557245.9037 5936833.7776</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:Polygon gml:id="Gml_3EED4EAB-578D-45C4-9146-B9F30A09A2FF" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">557096.392 5936828.69 557094.5259 5936699.1238 557245.9037 5936699.9855 
557243.4354 5936833.7776 557096.392 5936828.69 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:geltungsbereich>
      <xplan:planinhalt xlink:href="#Gml_7AC61FB9-43DD-45BE-B01F-8F443B5F303F" />
      <xplan:planinhalt xlink:href="#Gml_020FEBF9-58C5-48B2-8091-CCA81ABFB03A" />
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:gehoertZuPlan xlink:href="#Gml_CD6556C4-8BDB-43DA-821A-66FAE8B17BBA" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="Gml_7AC61FB9-43DD-45BE-B01F-8F443B5F303F">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>557094.5259 5936699.1238</gml:lowerCorner>
          <gml:upperCorner>557245.9037 5936833.7776</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_432F3BD2-639E-4A0F-826E-53D45A7209C6" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_88C08C3C-FFC3-4D4E-80CA-BD9D33FDD8A2" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">557096.392 5936828.69 557094.5259 5936699.1238 557245.9037 5936699.9855 
557243.4354 5936833.7776 557096.392 5936828.69 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:allgArtDerBaulNutzung>1000</xplan:allgArtDerBaulNutzung>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_AnpflanzungBindungErhaltung gml:id="Gml_020FEBF9-58C5-48B2-8091-CCA81ABFB03A">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>557151.6116 5936486.4055</gml:lowerCorner>
          <gml:upperCorner>557195.2679 5936529.2681</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_432F3BD2-639E-4A0F-826E-53D45A7209C6" />
      <xplan:rechtscharakter>9998</xplan:rechtscharakter>
      <xplan:position>
        <gml:LineString gml:id="Gml_B7809758-B6D6-46F3-B812-315B1B20CB5B" srsName="EPSG:25832">
          <gml:posList>557151.6116 5936529.2681 557152.4053 5936487.5962 557195.2679 5936486.4055 
</gml:posList>
        </gml:LineString>
      </xplan:position>
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
      <xplan:massnahme>2000</xplan:massnahme>
    </xplan:BP_AnpflanzungBindungErhaltung>
  </gml:featureMember>
</xplan:XPlanAuszug>