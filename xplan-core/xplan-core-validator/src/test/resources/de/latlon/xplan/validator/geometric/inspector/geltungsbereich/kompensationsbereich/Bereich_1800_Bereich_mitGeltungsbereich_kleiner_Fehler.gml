<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/6/0" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" gml:id="GML_f343e75d-107b-493b-8c0a-8ba23b8e7466" xsi:schemaLocation="http://www.xplanung.de/xplangml/6/0 https://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/6.0/XPlanung-Operationen.xsd">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25833">
      <gml:lowerCorner>464353.5384 5794554.254</gml:lowerCorner>
      <gml:upperCorner>464614.4181 5794790.7919</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_001">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>464350 5794550</gml:lowerCorner>
          <gml:upperCorner>464610 5794790</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>BPlan_5-4</xplan:name>
      <xplan:nummer>1</xplan:nummer>	  
      <xplan:technHerstellDatum>2021-07-01</xplan:technHerstellDatum>
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:bezugshoehe uom="m" >10</xplan:bezugshoehe>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25833" gml:id="GML_001a">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25833" gml:id="GML_001aa">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">464434 5794752 464424 5794754 464434 5794740 464434 5794752</gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>02000000</xplan:ags>
		  <xplan:gemeindeName>Testgemeinde</xplan:gemeindeName>
          <xplan:ortsteilName>Testortsteil</xplan:ortsteilName>		  
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:rechtsstand>3000</xplan:rechtsstand>
	  <xplan:inkrafttretensDatum>2021-07-01</xplan:inkrafttretensDatum>	
      <xplan:bereich xlink:href="#GML_004" />
      <xplan:bereich xlink:href="#GML_004_1800" />	  
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_004">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>464350 5794550</gml:lowerCorner>
          <gml:upperCorner>464610 5794790</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
	  <xplan:planinhalt xlink:href="#GML_005" />
	  <xplan:gehoertZuPlan xlink:href="#GML_001" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_004_1800">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>464350 5794550</gml:lowerCorner>
          <gml:upperCorner>464610 5794790</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:bedeutung>1800</xplan:bedeutung>
	  <xplan:geltungsbereich>
        <gml:MultiSurface srsName="EPSG:25833" gml:id="GML_004_1800a">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25833" gml:id="GML_004_1800aa">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">464424 5794754 464427 5794742 464434 5794740 464424 5794754</gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:geltungsbereich>  
	  <xplan:planinhalt xlink:href="#GML_005x" />
	  <xplan:gehoertZuPlan xlink:href="#GML_001" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_005">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>464350 5794550</gml:lowerCorner>
          <gml:upperCorner>464610 5794790</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_004" />
	  <xplan:rechtscharakter>1000</xplan:rechtscharakter>
	  <xplan:position>
        <gml:Polygon srsName="EPSG:25833" gml:id="GML_005a">
          <gml:exterior>
            <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">464434 5794752 464424 5794754 464434 5794740 464434 5794752</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
	  <xplan:allgArtDerBaulNutzung>1000</xplan:allgArtDerBaulNutzung>
      <xplan:besondereArtDerBaulNutzung>1000</xplan:besondereArtDerBaulNutzung>
      <!-- <xplan:sondernutzung>
        <xplan:BP_KomplexeSondernutzung>
          <xplan:allgemein>1000</xplan:allgemein>
        </xplan:BP_KomplexeSondernutzung>
	  </xplan:sondernutzung>  -->
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_WaldFlaeche gml:id="GML_005x">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25833">
          <gml:lowerCorner>464350 5794550</gml:lowerCorner>
          <gml:upperCorner>464610 5794790</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:gehoertZuBereich xlink:href="#GML_004_1800" />
	  <xplan:rechtscharakter>1000</xplan:rechtscharakter>
	  <xplan:position>
        <gml:Polygon srsName="EPSG:25833" gml:id="GML_005xa">
          <gml:exterior>
            <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">464424 5794754 464425 5794745 464427 5794742 464434 5794740 464424 5794754</gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:zweckbestimmung>
        <xplan:BP_KomplexeZweckbestWald>
          <xplan:allgemein>1000</xplan:allgemein>
        </xplan:BP_KomplexeZweckbestWald>
	  </xplan:zweckbestimmung>
    </xplan:BP_WaldFlaeche>
  </gml:featureMember>
</xplan:XPlanAuszug>