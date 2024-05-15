<?xml version="1.0" encoding="utf-8"?>
<xplan:XPlanAuszug xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="Gml_CA82752D-06DC-4234-A474-5633655B9E1D" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/2 http://www.xplanungwiki.de/upload/XPlanGML/5.2/Schema/XPlanung-Operationen.xsd" xmlns:xplan="http://www.xplanung.de/xplangml/5/2">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>574488.0919 5947222.4084</gml:lowerCorner>
      <gml:upperCorner>574572.0973 5947320.3044</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="Gml_9D7520F2-A4E5-434B-93EF-2C738E178FD4">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574488.0919 5947222.4084</gml:lowerCorner>
          <gml:upperCorner>574572.0973 5947320.3044</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Flächenschluss Test</xplan:name>
      <xplan:raeumlicherGeltungsbereich>
        <gml:Polygon gml:id="Gml_6547812D-425A-471D-A56C-9625E362E7F0" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574488.0919 5947222.4084 574572.0973 5947222.4084 574572.0973 5947320.3044 
574488.0919 5947320.3044 574488.0919 5947222.4084 </gml:posList>
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
      <xplan:bereich xlink:href="#Gml_A4C95CD1-A81A-46E3-9CA8-82A1E240971E" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="Gml_A4C95CD1-A81A-46E3-9CA8-82A1E240971E">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574488.0919 5947222.4084</gml:lowerCorner>
          <gml:upperCorner>574572.0973 5947320.3044</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>0</xplan:nummer>
      <xplan:geltungsbereich>
        <gml:Polygon gml:id="Gml_136CCF11-D460-46C8-91B3-8B07261A0993" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574488.0919 5947222.4084 574572.0973 5947222.4084 574572.0973 5947320.3044 
574488.0919 5947320.3044 574488.0919 5947222.4084 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:geltungsbereich>
      <xplan:planinhalt xlink:href="#Gml_DF52FAAB-C033-4F47-ADD0-B6C1FFFDDC33" />
      <xplan:planinhalt xlink:href="#Gml_B2020729-1896-4B43-AFD7-64946678D98E" />
      <xplan:gehoertZuPlan xlink:href="#Gml_9D7520F2-A4E5-434B-93EF-2C738E178FD4" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="Gml_DF52FAAB-C033-4F47-ADD0-B6C1FFFDDC33">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574488.0919 5947277.5962</gml:lowerCorner>
          <gml:upperCorner>574572.0973 5947320.3044</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_A4C95CD1-A81A-46E3-9CA8-82A1E240971E" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_FBBB66AB-F065-44F0-8BF6-01C8C55915CD" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574488.0919 5947320.3044 574488.0919 5947277.5962 574511.6399 5947278.1253 
574522.4868 5947287.1212 574572.0973 5947286.8566 574572.0973 5947320.3044 
574488.0919 5947320.3044 </gml:posList>
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
    <xplan:BP_BaugebietsTeilFlaeche gml:id="Gml_B2020729-1896-4B43-AFD7-64946678D98E">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>574488.0919 5947222.4084</gml:lowerCorner>
          <gml:upperCorner>574572.0973 5947287.1212</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#Gml_A4C95CD1-A81A-46E3-9CA8-82A1E240971E" />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon gml:id="Gml_D2B0DCA7-2E28-46E2-B773-F0E8F4CD8F41" srsName="EPSG:25832">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsName="EPSG:25832">574488.0919 5947277.5962 574488.0919 5947222.4084 574572.0973 5947222.4084 
574572.0973 5947286.8566 574522.4878 5947287.1212 574511.6399 5947278.1253 
574488.0919 5947277.5962 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:allgArtDerBaulNutzung>2000</xplan:allgArtDerBaulNutzung>
      <xplan:besondereArtDerBaulNutzung>1500</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
</xplan:XPlanAuszug>
