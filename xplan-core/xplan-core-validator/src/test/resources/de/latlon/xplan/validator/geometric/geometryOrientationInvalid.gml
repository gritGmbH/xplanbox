<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<XPlanAuszug xmlns:xplan="http://www.xplanung.de/xplangml/5/1" xmlns:gml="http://www.opengis.net/gml/3.2"
             xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://www.xplanung.de/xplangml/5/1 file:///D:/Daten/Dev/XPlanGML/Version_5.1/Spezifikation/XPlanGML_5_1_Schema/XPlanung-Operationen.xsd"
             gml:id="GML_1a978dea-a2b0-4821-92a3-2cb15b4de112" xmlns="http://www.xplanung.de/xplangml/5/1">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:25832">
      <gml:lowerCorner>572078.872 5938478.965</gml:lowerCorner>
      <gml:upperCorner>572225.523 5938671.367</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Plan gml:id="GML_fe335223-6664-4a70-bcdb-fc56b0c4c99c">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572078.872 5938478.965</gml:lowerCorner>
          <gml:upperCorner>572225.523 5938671.367</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:name>Laufrichtung</xplan:name>
      <xplan:erstellungsMassstab>1000</xplan:erstellungsMassstab>
      <xplan:raeumlicherGeltungsbereich>
        <gml:MultiSurface srsName="EPSG:25832" gml:id="GML_bce46968-7f95-405c-acad-6e5c9674a98c">
          <gml:surfaceMember>
            <gml:Polygon srsName="EPSG:25832" gml:id="GML_7ce62277-0aa1-4b4f-a50c-aae9728286f4">
              <gml:exterior>
                <gml:Ring>
                  <gml:curveMember>
                    <gml:Curve srsName="EPSG:25832" gml:id="GML_2736d7d0-4494-48c2-86ea-635130e68121">
                      <gml:segments>
                        <gml:LineStringSegment interpolation="linear">
                          <gml:posList srsDimension="2" count="7">572209.380 5938582.980 572183.015 5938671.367
                            572166.198 5938665.141 572135.549 5938653.793 572083.047 5938634.355 572078.872 5938625.272
                            572116.844 5938522.692
                          </gml:posList>
                        </gml:LineStringSegment>
                        <gml:ArcString interpolation="circularArc3Points">
                          <gml:posList srsDimension="2" count="7">572116.844 5938522.692 572119.846 5938515.711
                            572123.546 5938509.074 572130.282 5938501.276 572139.043 5938495.852 572146.175 5938493.244
                            572153.536 5938491.380
                          </gml:posList>
                        </gml:ArcString>
                        <gml:LineStringSegment interpolation="linear">
                          <gml:posList srsDimension="2" count="3">572153.536 5938491.380 572168.119 5938488.476
                            572194.546 5938483.212
                          </gml:posList>
                        </gml:LineStringSegment>
                        <gml:ArcString interpolation="circularArc3Points">
                          <gml:posList srsDimension="2" count="5">572194.546 5938483.212 572195.728 5938482.968
                            572196.907 5938482.708 572203.063 5938481.062 572209.080 5938478.965
                          </gml:posList>
                        </gml:ArcString>
                        <gml:LineStringSegment interpolation="linear">
                          <gml:posList srsDimension="2" count="4">572209.080 5938478.965 572225.523 5938528.858
                            572213.862 5938567.956 572209.380 5938582.980
                          </gml:posList>
                        </gml:LineStringSegment>
                      </gml:segments>
                    </gml:Curve>
                  </gml:curveMember>
                </gml:Ring>
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
      <xplan:verfahren>1000</xplan:verfahren>
      <xplan:rechtsstand>2000</xplan:rechtsstand>
      <xplan:veraenderungssperre>false</xplan:veraenderungssperre>
      <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
      <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
      <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
      <xplan:gruenordnungsplan>false</xplan:gruenordnungsplan>
      <xplan:bereich xlink:href="#GML_e71a8674-27f4-49db-b10c-76917ec8d943"/>
    </xplan:BP_Plan>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_Bereich gml:id="GML_e71a8674-27f4-49db-b10c-76917ec8d943">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572096.049 5938603.521</gml:lowerCorner>
          <gml:upperCorner>572191.282 5938571.917</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:nummer>1</xplan:nummer>
      <xplan:planinhalt xlink:href="#GML_88258139-e3ff-4388-9838-a30775e1f8bf"/>
      <xplan:gehoertZuPlan xlink:href="#GML_fe335223-6664-4a70-bcdb-fc56b0c4c99c"/>
    </xplan:BP_Bereich>
  </gml:featureMember>
  <gml:featureMember>
    <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_88258139-e3ff-4388-9838-a30775e1f8bf">
      <gml:boundedBy>
        <gml:Envelope srsName="EPSG:25832">
          <gml:lowerCorner>572078.872 5938482.708</gml:lowerCorner>
          <gml:upperCorner>572210.848 5938665.141</gml:upperCorner>
        </gml:Envelope>
      </gml:boundedBy>
      <xplan:ebene>0</xplan:ebene>
      <xplan:gehoertZuBereich xlink:href="#GML_e71a8674-27f4-49db-b10c-76917ec8d943"/>
      <xplan:rechtscharakter>1000</xplan:rechtscharakter>
      <xplan:position>
        <gml:Polygon srsName="EPSG:25832" gml:id="GML_45ddaf05-73a8-42dd-b7ef-fe3549c449e1">
          <gml:exterior>
            <gml:Ring>
              <gml:curveMember>
                <gml:Curve srsName="EPSG:25832" gml:id="GML_1ba62067-b84e-4568-a5f1-ab67b514f852">
                  <gml:segments>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="7">
                        572078.872 5938625.272
                        572083.047 5938634.355
                        572135.549 5938653.793
                        572166.198 5938665.141
                        572194.430 5938578.006
                        572210.848 5938527.335
                        572196.907 5938482.708
                      </gml:posList>
                    </gml:LineStringSegment>
                    <gml:ArcString interpolation="circularArc3Points">
                      <gml:posList srsDimension="2" count="3">
                        572196.907 5938482.708
                        572195.728 5938482.968
                        572194.546 5938483.212
                      </gml:posList>
                    </gml:ArcString>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="3">
                        572194.546 5938483.212
                        572168.119 5938488.476
                        572153.536 5938491.380
                      </gml:posList>
                    </gml:LineStringSegment>
                    <gml:ArcString interpolation="circularArc3Points">
                      <gml:posList srsDimension="2" count="7">
                        572153.536 5938491.380
                        572146.175 5938493.244
                        572139.043 5938495.852
                        572130.282 5938501.276
                        572123.546 5938509.074
                        572119.846 5938515.711
                        572116.844 5938522.692
                      </gml:posList>
                    </gml:ArcString>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="2">
                        572116.844 5938522.692
                        572078.872 5938625.272
                      </gml:posList>
                    </gml:LineStringSegment>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
            </gml:Ring>
          </gml:exterior>
          <gml:interior>
            <gml:Ring>
              <gml:curveMember>
                <gml:Curve srsName="EPSG:25832" gml:id="GML_e1176420-6223-4f27-ba63-acc8dfed2329">
                  <gml:segments>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="4">
                        572117.933 5938592.407
                        572111.552 5938575.664
                        572129.681 5938559.849
                        572175.612 5938578.049
                      </gml:posList>
                    </gml:LineStringSegment>
                    <gml:ArcString interpolation="circularArc3Points">
                      <gml:posList srsDimension="2" count="3">
                        572175.612 5938578.049
                        572173.569 5938595.846
                        572162.867 5938610.212
                      </gml:posList>
                    </gml:ArcString>
                    <gml:LineStringSegment interpolation="linear">
                      <gml:posList srsDimension="2" count="2">
                        572162.867 5938610.212
                        572117.933 5938592.407
                      </gml:posList>
                    </gml:LineStringSegment>
                  </gml:segments>
                </gml:Curve>
              </gml:curveMember>
            </gml:Ring>
          </gml:interior>
        </gml:Polygon>
      </xplan:position>
      <xplan:flaechenschluss>true</xplan:flaechenschluss>
      <xplan:GRZ>0.4</xplan:GRZ>
      <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
    </xplan:BP_BaugebietsTeilFlaeche>
  </gml:featureMember>
</XPlanAuszug>
