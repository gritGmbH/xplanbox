<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<!--Erstellt von WS LANDCAD am 26.07.2022
	Grünfläche wird vollständig von der Baugebietsteilfläche überlappt und hat identische Stützpunkte wie die Baugebietsteilfläche.
-->
<xplan:XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/5/2" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wfs="http://www.opengis.net/wfs" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xsi:schemaLocation="http://www.xplanung.de/xplangml/5/2 http://repository.gdi-de.org/schemas/de.xleitstelle.xplanung/5.2/XPlanung-Operationen.xsd" gml:id="GML_0bffa49b-a534-4a50-b718-5bc37a0ad3b5">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>2180.523 1073.316</gml:lowerCorner>
      <gml:upperCorner>2206.360 1092.100</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_41d0ea93-cd5a-40c5-95fd-1df15ec46c3d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>2180.523 1073.316</gml:lowerCorner>
          <gml:upperCorner>2206.360 1092.100</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>xplan52_Flaechenschlussfehler_vollstaendige_Ueberlappung</xplan:name>
      <xplan:aendert />
      <xplan:wurdeGeaendertVon />
      <xplan:erstellungsMassstab>500</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_35b5b1f6-8241-4ee1-af35-fbdb32e03e70">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_1e7bcab8-b098-4e5a-b881-9160ef6bc949">
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList srsDimension="2" count="9">2191.642 1092.100 2188.953 1088.388 2184.789 1082.641 2180.523 1076.753 2199.481 1073.316 2201.325 1077.601 2204.409 1084.767 2206.360 1089.302 2191.642 1092.100 </gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </xplan:raeumlicherGeltungsbereich>
      <xplan:verfahrensMerkmale />
      <xplan:gemeinde>
        <xplan:XP_Gemeinde>
          <xplan:ags>05158008</xplan:ags>
          <xplan:gemeindeName>Haan</xplan:gemeindeName>
        </xplan:XP_Gemeinde>
      </xplan:gemeinde>
      <xplan:plangeber />
      <xplan:planArt>10001</xplan:planArt>
      <xplan:veraenderungssperre>false</xplan:veraenderungssperre>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#GML_6387e27b-c03f-4af1-82b6-42e7666b609d" />
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_6387e27b-c03f-4af1-82b6-42e7666b609d">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>2180.523 1073.316</gml:lowerCorner>
          <gml:upperCorner>2206.360 1092.100</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:planinhalt xlink:href="#GML_a7a48503-d117-42b7-914f-0a20c862a54e" />
      <xplan:planinhalt xlink:href="#GML_349d357a-cb9a-4a26-a946-908647eb1bad" />
      <xplan:gehoertZuPlan xlink:href="#GML_41d0ea93-cd5a-40c5-95fd-1df15ec46c3d" />
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_GruenFlaeche gml:id="GML_a7a48503-d117-42b7-914f-0a20c862a54e">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>2184.789 1077.601</gml:lowerCorner>
          <gml:upperCorner>2204.409 1088.388</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_6387e27b-c03f-4af1-82b6-42e7666b609d" />
      <xplan:startBedingung />
      <xplan:endeBedingung />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_b955d9c7-b039-4ffd-ac10-528489a7e5f3">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="5">2184.789 1082.641 2201.325 1077.601 2204.409 1084.767 2188.953 1088.388 2184.789 1082.641 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:nutzungsform>1000</xplan:nutzungsform>
    </xplan:BP_GruenFlaeche>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_349d357a-cb9a-4a26-a946-908647eb1bad">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>2180.523 1073.316</gml:lowerCorner>
          <gml:upperCorner>2206.360 1092.100</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:text>An der rückwärtigen Grundstücksgrenze ist eine Grenzbebauung als abweichende Bauweise festgesetzt</xplan:text>
      <xplan:gliederung1>WA</xplan:gliederung1>
      <xplan:ebene>0</xplan:ebene>
      <xplan:hoehenangabe>
        <xplan:XP_Hoehenangabe>
          <xplan:hoehenbezug>1100</xplan:hoehenbezug>
          <xplan:bezugspunkt>6000</xplan:bezugspunkt>
          <xplan:h uom="m">185</xplan:h>
        </xplan:XP_Hoehenangabe>
      </xplan:hoehenangabe>
      <xplan:gehoertZuBereich xlink:href="#GML_6387e27b-c03f-4af1-82b6-42e7666b609d" />
      <xplan:startBedingung />
      <xplan:endeBedingung />
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_ad435c99-f0fe-4fa7-9f15-5ce6a9cf7f02">
          <gml:exterior>
            <gml:LinearRing>
              <gml:posList srsDimension="2" count="9">2191.642 1092.100 2188.953 1088.388 2184.789 1082.641 2180.523 1076.753 2199.481 1073.316 2201.325 1077.601 2204.409 1084.767 2206.360 1089.302 2191.642 1092.100 </gml:posList>
            </gml:LinearRing>
          </gml:exterior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:dachgestaltung />
      <xplan:allgArtDerBaulNutzung>1000</xplan:allgArtDerBaulNutzung>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
      <xplan:bauweise>2000</xplan:bauweise>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
</xplan:XPlanAuszug>
