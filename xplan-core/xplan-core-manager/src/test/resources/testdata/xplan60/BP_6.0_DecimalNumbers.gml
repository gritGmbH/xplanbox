<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!-- 
  Testfälle
  - versionBauGB in BP_Plan
  - XP_SpezExterneReferenz mit Pflichtattributen
-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/6/0" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" gml:id="GML_f343e75d-107b-493b-8c0a-8ba23b8e7466" xsi:schemaLocation="http://www.xplanung.de/xplangml/6/0 https://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/6.0/XPlanung-Operationen.xsd">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>564984.128 5940442.486</gml:lowerCorner>
      <gml:upperCorner>565072.441 5940522.630</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>564984.128 5940442.486</gml:lowerCorner>
          <gml:upperCorner>565072.441 5940522.630</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>BPlan002_6-0-NKS</xplan:name>
      <xplan:beschreibung>Testdaten</xplan:beschreibung>
      <xplan:technHerstellDatum>2021-07-01</xplan:technHerstellDatum>
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_a4b76c05-e841-441f-9284-0d28c0bd2502">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_63208495-2dad-4690-a072-f31868f7eece">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="5">564984.1281112354 5940442.48611 565072.44111 5940442.48611 565072.44111 5940522.63011 564984.12811 5940522.63011 564984.1281112354 5940442.48611 </gml:posList>
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
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:planArt>1000</xplan:planArt>
      <xplan:rechtsstand>3000</xplan:rechtsstand>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:versionBauGB>
        <xplan:XP_GesetzlicheGrundlage>
          <xplan:name>BaugGB</xplan:name>
          <xplan:datum>2004-03-02</xplan:datum>
          <xplan:detail>§10</xplan:detail>
        </xplan:XP_GesetzlicheGrundlage>
      </xplan:versionBauGB>
      <xplan:bereich xlink:href="#GML_95a8b21b-8754-4350-9041-213259262fd8" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_95a8b21b-8754-4350-9041-213259262fd8">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>564984.128 5940442.486</gml:lowerCorner>
          <gml:upperCorner>565072.441 5940511.714</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:gehoertZuPlan xlink:href="#GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be" />
    </xplan:BP_Bereich>
  </gml:featureMember>
</xplan:XPlanAuszug>