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

<!--Erzeugt mit KIT (www.kit.edu) GML-Toolbox, Erstellungsdatum: 12/07/16-->
<xplan:XPlanAuszug xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.xplanung.de/xplangml/5/0 ../../Schema/XPlanung-Operationen.xsd"
 xmlns:xplan="http://www.xplanung.de/xplangml/5/0" xmlns:wfs="http://www.opengis.net/wfs"
 xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:adv="http://www.adv-online.de/nas"
 gml:id="GML_40adb0a5-8ba6-478e-8384-c1939d2711c7">
 <gml:boundedBy>
  <gml:Envelope srsName="EPSG:31467">
   <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
   <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
  </gml:Envelope>
 </gml:boundedBy>
 <gml:featureMember>
  <xplan:BP_BauGrenze gml:id="GML_1265b858-4bfa-4694-94dd-f3b3cbd9ee03">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954683.088 5499947.56</gml:lowerCorner>
     <gml:upperCorner>3954716.126 5499966.522</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:LineString gml:id="GML_e098a71d-b6d5-4adc-b96d-83a194dcacac">
     <gml:posList srsDimension="2" count="11">3954715.608 5499966.522 3954716.126 5499951.526
      3954710.115 5499951.17 3954701.227 5499950.272 3954692.491 5499948.856 3954686.404 5499947.56
      3954683.088 5499962.19 3954689.727 5499963.604 3954699.271 5499965.15 3954708.916 5499966.125
      3954715.608 5499966.522 </gml:posList>
    </gml:LineString>
   </xplan:position>
   <xplan:refBegruendungInhalt xlink:href="#GML_REFBEGRUENDUNGINHALT" />
  </xplan:BP_BauGrenze>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:XP_BegruendungAbschnitt gml:id="GML_REFBEGRUENDUNGINHALT">
   <xplan:schluessel>key</xplan:schluessel>
  </xplan:XP_BegruendungAbschnitt>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_BauGrenze gml:id="GML_55fd715c-da0f-49b7-8a0c-5acf02c4f4b8">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954741.587 5499951.358</gml:lowerCorner>
     <gml:upperCorner>3954754.591 5499966.151</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:LineString gml:id="GML_8c063d43-445f-4609-84a3-b15ce5179846">
     <gml:posList srsDimension="2" count="6">3954741.594 5499951.358 3954741.587 5499966.151
      3954746.201 5499965.746 3954754.58 5499964.589 3954754.591 5499951.369 3954741.594 5499951.358
     </gml:posList>
    </gml:LineString>
   </xplan:position>
  </xplan:BP_BauGrenze>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_013e660f-385d-4338-b9f2-85f30845ec26">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954678.828 5499913.525</gml:lowerCorner>
     <gml:upperCorner>3954720.33 5499972.698</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:uuid>PZV_01.01.03</xplan:uuid>
   <xplan:ebene>0</xplan:ebene>
   <xplan:hatGenerAttribut>
    <xplan:XP_StringAttribut>
     <xplan:name>MeinAttribut</xplan:name>
     <xplan:wert>Dies ist ein Beispiel für ein generisches Attribut</xplan:wert>
    </xplan:XP_StringAttribut>
   </xplan:hatGenerAttribut>
   <xplan:externeReferenz>
    <xplan:XP_SpezExterneReferenz>
     <xplan:art>Dokument</xplan:art>
     <xplan:referenzURL>http://www.Demogemeinde.de/BPlaene/PlanzlisteNeubaugebietA1.pdf</xplan:referenzURL>
     <xplan:typ>2200</xplan:typ>
    </xplan:XP_SpezExterneReferenz>
   </xplan:externeReferenz>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_930007f4-520d-4280-b495-bbf814b7daef">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="9">3954718.396 5499972.698 3954708.436 5499972.107
        3954698.489 5499971.102 3954688.621 5499969.503 3954678.828 5499967.417 3954691.042
        5499913.525 3954704.427 5499914.98 3954720.33 5499916.71 3954718.396 5499972.698
       </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
   <xplan:GFZ>1.2</xplan:GFZ>
   <xplan:GRZ>0.4</xplan:GRZ>
   <xplan:Z>3</xplan:Z>
   <xplan:besondereArtDerBaulNutzung>2100</xplan:besondereArtDerBaulNutzung>
   <xplan:sondernutzung>1500</xplan:sondernutzung>
  </xplan:BP_BaugebietsTeilFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_cdb8ddd5-f73a-4d10-b1d0-3092036af965">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954767.319 5499923.714</gml:lowerCorner>
     <gml:upperCorner>3954791.49 5499967.563</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_d248df9b-b372-45a2-872b-06bb0cb26e08">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="10">3954791.49 5499929.16 3954791.478 5499946.05
        3954791.458 5499967.086 3954775.589 5499967.563 3954771.438 5499966.536 3954769.026
        5499964.03 3954767.584 5499960.028 3954767.319 5499945.964 3954767.608 5499923.714
        3954791.49 5499929.16 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
   <xplan:GFZ>0.6</xplan:GFZ>
   <xplan:GRZ>0.6</xplan:GRZ>
   <xplan:Zmin>3</xplan:Zmin>
   <xplan:Zmax>5</xplan:Zmax>
   <xplan:besondereArtDerBaulNutzung>1500</xplan:besondereArtDerBaulNutzung>
   <xplan:vertikaleDifferenzierung>false</xplan:vertikaleDifferenzierung>
  </xplan:BP_BaugebietsTeilFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_BaugebietsTeilFlaeche gml:id="GML_e346d550-1247-4012-91f4-df3f4f5d3b1a">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954718.396 5499916.71</gml:lowerCorner>
     <gml:upperCorner>3954760.613 5499972.787</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:uuid>{45AD741C-A18A-4C16-900B-C806E981151E}</xplan:uuid>
   <xplan:ebene>0</xplan:ebene>
   <xplan:hoehenangabe>
    <xplan:XP_Hoehenangabe>
     <xplan:hoehenbezug>3000</xplan:hoehenbezug>
     <xplan:bezugspunkt>2000</xplan:bezugspunkt>
     <xplan:hMin uom="m">6</xplan:hMin>
     <xplan:hMax uom="m">9</xplan:hMax>
    </xplan:XP_Hoehenangabe>
   </xplan:hoehenangabe>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_e6e52dc5-1f5d-4bc9-9ce7-087f430386c3">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="12">3954755.146 5499970.567 3954746.875 5499971.71
        3954738.584 5499972.437 3954728.477 5499972.787 3954718.396 5499972.698 3954720.33
        5499916.71 3954738.608 5499920.445 3954760.613 5499924.935 3954760.581 5499964.154
        3954759.869 5499967.046 3954757.919 5499969.393 3954755.146 5499970.567 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
   <xplan:GFZ>0.8</xplan:GFZ>
   <xplan:besondereArtDerBaulNutzung>1200</xplan:besondereArtDerBaulNutzung>
   <xplan:abweichungBauNVO>2000</xplan:abweichungBauNVO>
   <xplan:bauweise>2000</xplan:bauweise>
   <xplan:vertikaleDifferenzierung>false</xplan:vertikaleDifferenzierung>
  </xplan:BP_BaugebietsTeilFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_Bereich gml:id="GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
     <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:nummer>0</xplan:nummer>
   <xplan:name>defaultBereich</xplan:name>
   <xplan:planinhalt xlink:href="#GML_4251b3b6-9e6b-4075-a72a-b09131fabb87"/>
   <xplan:planinhalt xlink:href="#GML_e67225c0-fbde-4eb3-beb3-6171a18e863e"/>
   <xplan:planinhalt xlink:href="#GML_c9b5a421-2d88-4e03-9c0f-d6dba7e14beb"/>
   <xplan:planinhalt xlink:href="#GML_013e660f-385d-4338-b9f2-85f30845ec26"/>
   <xplan:planinhalt xlink:href="#GML_e346d550-1247-4012-91f4-df3f4f5d3b1a"/>
   <xplan:planinhalt xlink:href="#GML_55fd715c-da0f-49b7-8a0c-5acf02c4f4b8"/>
   <xplan:planinhalt xlink:href="#GML_1265b858-4bfa-4694-94dd-f3b3cbd9ee03"/>
   <xplan:planinhalt xlink:href="#GML_957e82d8-f217-4620-a573-ae076b1cd9ba"/>
   <xplan:planinhalt xlink:href="#GML_cdb8ddd5-f73a-4d10-b1d0-3092036af965"/>
   <xplan:planinhalt xlink:href="#GML_5ed7abfd-7ccd-41a7-bbb2-3a8e3ba28735"/>
   <xplan:planinhalt xlink:href="#GML_387ba2c0-d7e5-401d-a298-584375407531"/>
   <xplan:planinhalt xlink:href="#GML_770a344c-6a95-401f-b7a0-b612d7c0f61a"/>
   <xplan:planinhalt xlink:href="#GML_ee9b3332-16db-4f62-951a-c26587830255"/>
   <xplan:praesentationsobjekt xlink:href="#GML_b4e47d29-d21c-42ab-85b7-b12ea57e89f2"/>
   <xplan:praesentationsobjekt xlink:href="#GML_f740a6e7-2813-4392-a050-e744872125c6"/>
   <xplan:versionBauNVODatum>2002-11-03</xplan:versionBauNVODatum>
   <xplan:versionBauNVOText>Verordnung über die bauliche Nutzung der Grundstücke
    (Baunutzungsverordnung - BauNVO)</xplan:versionBauNVOText>
   <xplan:versionBauGBDatum>2004-09-23</xplan:versionBauGBDatum>
   <xplan:versionBauGBText>"Baugesetzbuch in der Fassung der Bekanntmachung vom 23. September 2004</xplan:versionBauGBText>
   <xplan:gehoertZuPlan xlink:href="#GML_4ad825d0-5819-4126-be9d-f1e0b885374c"/>
  </xplan:BP_Bereich>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_GemeinbedarfsFlaeche gml:id="GML_c9b5a421-2d88-4e03-9c0f-d6dba7e14beb">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954721.58 5499865.665</gml:lowerCorner>
     <gml:upperCorner>3954760.628 5499917.469</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>   
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:refTextInhalt xlink:href="#TextAbschnitt_A"/>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_6975ce84-d5ba-4dbe-91c1-cff20a3f8a8d">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="10">3954760.628 5499900.031 3954760.616 5499917.469
        3954721.58 5499909.522 3954725.947 5499884.456 3954735.247 5499880.877 3954748.06
        5499870.089 3954752.164 5499865.665 3954756.426 5499876.55 3954759.349 5499888.134
        3954760.628 5499900.031 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
   <xplan:zweckbestimmung>16000</xplan:zweckbestimmung>
  </xplan:BP_GemeinbedarfsFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_GewaesserFlaeche gml:id="GML_770a344c-6a95-401f-b7a0-b612d7c0f61a">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954644.518 5499890.192</gml:lowerCorner>
     <gml:upperCorner>3954673.395 5499904.325</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_267de2cc-acfd-430a-b19f-271ead11704f">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="98">
        3954646.931 5499891.12 
        3954651.521 5499890.574
        3954655.789 5499890.213 3954656.138 5499890.194 3954656.488 5499890.192 3954656.745
        5499890.206 3954657.001 5499890.24 3954657.253 5499890.291 3954657.501 5499890.362
        3954657.744 5499890.45 3954657.979 5499890.556 3954658.477 5499890.78 3954658.991
        5499890.965 3954659.517 5499891.108 3954660.053 5499891.211 3954660.682 5499891.284
        3954661.313 5499891.313 3954661.945 5499891.3 3954663.356 5499891.271 3954664.765
        5499891.338 3954665.425 5499891.416 3954666.079 5499891.539 3954666.721 5499891.708
        3954668.308 5499892.238 3954669.86 5499892.866 3954670.32 5499893.093 3954670.759
        5499893.358 3954671.174 5499893.66 3954671.561 5499893.996 3954671.919 5499894.363
        3954672.244 5499894.76 3954672.534 5499895.183 3954672.787 5499895.629 3954673.002
        5499896.095 3954673.175 5499896.578 3954673.285 5499896.989 3954673.358 5499897.407
        3954673.395 5499897.83 3954673.395 5499898.255 3954673.359 5499898.679 3954673.287
        5499899.097 3954673.178 5499899.508 3954673.035 5499899.908 3954672.858 5499900.294
        3954672.648 5499900.663 3954672.407 5499901.013 3954671.982 5499901.533 3954671.517
        5499902.019 3954671.016 5499902.466 3954670.482 5499902.873 3954669.918 5499903.237
        3954669.327 5499903.556 3954668.749 5499903.81 3954668.152 5499904.016 3954667.54 5499904.17
        3954666.917 5499904.274 3954666.288 5499904.325 3954665.657 5499904.323 3954661.186
        5499904.015 3954660.043 5499903.879 3954658.907 5499903.687 3954655.748 5499903.045
        3954654.705 5499902.796 3954653.95 5499902.575 3954653.206 5499902.319 3954650.247
        5499901.141 3954649.356 5499900.723 3954648.498 5499900.243 3954647.569 5499899.639
        3954646.681 5499898.976 3954646.459 5499898.784 3954646.252 5499898.574 3954646.064
        5499898.347 3954645.894 5499898.106 3954645.744 5499897.853 3954645.359 5499897.064
        3954645.034 5499896.249 3954644.771 5499895.412 3954644.635 5499894.816 3954644.551
        5499894.212 3954644.518 5499893.602 3954644.538 5499892.991 3954644.609 5499892.385
        3954644.629 5499892.288 3954644.657 5499892.194 3954644.693 5499892.102 3954644.737
        5499892.013 3954644.788 5499891.929 3954644.846 5499891.849 3954644.911 5499891.775
        3954644.982 5499891.706 3954645.058 5499891.644 3954645.139 5499891.588 3954645.225
        5499891.54 3954645.315 5499891.499 3954645.408 5499891.465 3954646.163 5499891.263
        3954646.931 5499891.12 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
   <xplan:zweckbestimmung>1100</xplan:zweckbestimmung>
  </xplan:BP_GewaesserFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_GruenFlaeche gml:id="GML_957e82d8-f217-4620-a573-ae076b1cd9ba">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954633.369 5499881.862</gml:lowerCorner>
     <gml:upperCorner>3954760.616 5499924.935</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_9aa8fc86-9676-4d16-8348-009cf7fc2420">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="18">3954725.947 5499884.456 3954721.58 5499909.522
        3954760.616 5499917.469 3954760.613 5499924.935 3954738.608 5499920.445 3954720.33
        5499916.71 3954704.427 5499914.98 3954691.042 5499913.525 3954675.148 5499911.792
        3954659.071 5499908.004 3954639.089 5499903.299 3954633.369 5499901.13 3954637.888
        5499886.14 3954639.179 5499881.862 3954672.269 5499887.85 3954675.333 5499897.894
        3954702.538 5499893.471 3954725.947 5499884.456 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
     <gml:interior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="98">3954646.931 5499891.12 3954646.163 5499891.263
        3954645.408 5499891.465 3954645.315 5499891.499 3954645.225 5499891.54 3954645.139
        5499891.588 3954645.058 5499891.644 3954644.982 5499891.706 3954644.911 5499891.775
        3954644.846 5499891.849 3954644.788 5499891.929 3954644.737 5499892.013 3954644.693
        5499892.102 3954644.657 5499892.194 3954644.629 5499892.288 3954644.609 5499892.385
        3954644.538 5499892.991 3954644.518 5499893.602 3954644.551 5499894.212 3954644.635
        5499894.816 3954644.771 5499895.412 3954645.034 5499896.249 3954645.359 5499897.064
        3954645.744 5499897.853 3954645.894 5499898.106 3954646.064 5499898.347 3954646.252
        5499898.574 3954646.459 5499898.784 3954646.681 5499898.976 3954647.569 5499899.639
        3954648.498 5499900.243 3954649.356 5499900.723 3954650.247 5499901.141 3954653.206
        5499902.319 3954653.95 5499902.575 3954654.705 5499902.796 3954655.748 5499903.045
        3954658.907 5499903.687 3954660.043 5499903.879 3954661.186 5499904.015 3954665.657
        5499904.323 3954666.288 5499904.325 3954666.917 5499904.274 3954667.54 5499904.17
        3954668.152 5499904.016 3954668.749 5499903.81 3954669.327 5499903.556 3954669.918
        5499903.237 3954670.482 5499902.873 3954671.016 5499902.466 3954671.517 5499902.019
        3954671.982 5499901.533 3954672.407 5499901.013 3954672.648 5499900.663 3954672.858
        5499900.294 3954673.035 5499899.908 3954673.178 5499899.508 3954673.287 5499899.097
        3954673.359 5499898.679 3954673.395 5499898.255 3954673.395 5499897.83 3954673.358
        5499897.407 3954673.285 5499896.989 3954673.175 5499896.578 3954673.002 5499896.095
        3954672.787 5499895.629 3954672.534 5499895.183 3954672.244 5499894.76 3954671.919
        5499894.363 3954671.561 5499893.996 3954671.174 5499893.66 3954670.759 5499893.358
        3954670.32 5499893.093 3954669.86 5499892.866 3954668.308 5499892.238 3954666.721
        5499891.708 3954666.079 5499891.539 3954665.425 5499891.416 3954664.765 5499891.338
        3954663.356 5499891.271 3954661.945 5499891.3 3954661.313 5499891.313 3954660.682
        5499891.284 3954660.053 5499891.211 3954659.517 5499891.108 3954658.991 5499890.965
        3954658.477 5499890.78 3954657.979 5499890.556 3954657.744 5499890.45 3954657.501
        5499890.362 3954657.253 5499890.291 3954657.001 5499890.24 3954656.745 5499890.206
        3954656.488 5499890.192 3954656.138 5499890.194 3954655.789 5499890.213 3954651.521
        5499890.574 3954646.931 5499891.12 </gml:posList>
      </gml:LinearRing>
     </gml:interior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
   <xplan:zweckbestimmung>1000</xplan:zweckbestimmung>
   <xplan:zweckbestimmung>16000</xplan:zweckbestimmung>
   <xplan:detaillierteZweckbestimmung
    codeSpace="http://www.xplanungwiki.de/upload/XPlanGML/5.0.BetaV2/Schema/Codelists/xplan_BP_DetailZweckbestGruenFlaeche.xml"
    >SpezielleParkanlage</xplan:detaillierteZweckbestimmung>
  </xplan:BP_GruenFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_NutzungsartenGrenze gml:id="GML_e67225c0-fbde-4eb3-beb3-6171a18e863e">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954718.396 5499916.71</gml:lowerCorner>
     <gml:upperCorner>3954720.33 5499972.698</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:LineString gml:id="GML_fd9c1bae-0cf9-4e00-92d6-7226321da796">
     <gml:posList srsDimension="2" count="2">3954718.396 5499972.698 3954720.33 5499916.71
     </gml:posList>
    </gml:LineString>
   </xplan:position>
  </xplan:BP_NutzungsartenGrenze>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_Plan gml:id="Aenderungsplan_1">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954680 5499900</gml:lowerCorner>
     <gml:upperCorner>3954700 5499920</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:name>DemoPlanAenderung_1</xplan:name>
   <xplan:aendert>
    <xplan:XP_VerbundenerPlan>
     <xplan:planName>BPlan Demo-Gemeinde</xplan:planName>
     <xplan:rechtscharakter>1100</xplan:rechtscharakter>
    </xplan:XP_VerbundenerPlan>
   </xplan:aendert>
   <xplan:raeumlicherGeltungsbereich>
    <gml:Polygon srsName="EPSG:31467" gml:id="Aenderungsplan_1_Geltungsbereich">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="5">3954680 5499900 3954700 5499900 3954700 5499920
        3954680 5499920 3954680 5499900 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:raeumlicherGeltungsbereich>
   <xplan:texte xlink:href="#NeueTextlicheFestsetzung"/>
   <xplan:gemeinde>
    <xplan:XP_Gemeinde>
     <xplan:ags>1234567</xplan:ags>
     <xplan:gemeindeName>Demo-Gemeinde</xplan:gemeindeName>
    </xplan:XP_Gemeinde>
   </xplan:gemeinde>
   <xplan:planArt>1000</xplan:planArt>
   <xplan:rechtsstand>4000</xplan:rechtsstand>
   <xplan:inkrafttretensDatum>2007-04-01</xplan:inkrafttretensDatum>
  </xplan:BP_Plan>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_Plan gml:id="GML_4ad825d0-5819-4126-be9d-f1e0b885374c">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
     <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:name>BPlan Demo-Gemeinde</xplan:name>
   <xplan:technHerstellDatum>2006-06-22</xplan:technHerstellDatum>
   <xplan:wurdeGeaendertVon>
    <xplan:XP_VerbundenerPlan>
     <xplan:planName>DemoPlanAenderung_1</xplan:planName>
     <xplan:rechtscharakter>1100</xplan:rechtscharakter>
    </xplan:XP_VerbundenerPlan>
   </xplan:wurdeGeaendertVon>
   <xplan:bezugshoehe uom="m">110</xplan:bezugshoehe>
   <xplan:raeumlicherGeltungsbereich>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_125781e4-36ae-4d79-af38-44880d485b8b">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="35">3954678.828 5499967.417 3954691.042 5499913.525
        3954675.148 5499911.792 3954659.071 5499908.004 3954639.089 5499903.299 3954633.369
        5499901.13 3954637.888 5499886.14 3954639.179 5499881.862 3954672.269 5499887.85 3954675.333
        5499897.894 3954702.538 5499893.471 3954725.947 5499884.456 3954735.247 5499880.877
        3954748.06 5499870.089 3954752.164 5499865.665 3954754.756 5499862.867 3954757.256
        5499860.173 3954761.394 5499869.122 3954764.285 5499878.571 3954767.624 5499897.422
        3954767.609 5499918.603 3954767.608 5499923.714 3954791.49 5499929.16 3954791.478 5499946.05
        3954791.458 5499967.086 3954775.589 5499967.563 3954755.146 5499970.567 3954746.875
        5499971.71 3954738.584 5499972.437 3954728.477 5499972.787 3954718.396 5499972.698
        3954708.436 5499972.107 3954698.489 5499971.102 3954688.621 5499969.503 3954678.828
        5499967.417 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:raeumlicherGeltungsbereich>
   <xplan:externeReferenz>
    <xplan:XP_SpezExterneReferenz>
     <xplan:art>Dokument</xplan:art>
     <xplan:referenzURL>http://www.Demogemeinde.de/BPlaene/DemoplanBegruendung.pdf</xplan:referenzURL>
     <xplan:referenzMimeType
      codeSpace="http://www.xplanungwiki.de/upload/XPlanGML/5.0.BetaV2/Schema/Codelists/xplan_XP_MimeTypes.xml"
      >application/pdf</xplan:referenzMimeType>
     <xplan:typ>1000</xplan:typ>
    </xplan:XP_SpezExterneReferenz>
   </xplan:externeReferenz>
   <xplan:externeReferenz>
    <xplan:XP_SpezExterneReferenz>
     <xplan:art>Dokument</xplan:art>
     <xplan:referenzURL>http://www.Demogemeinde.de/BPlaene/Demoplan.pdf</xplan:referenzURL>
     <xplan:referenzMimeType
      codeSpace="http://www.xplanungwiki.de/upload/XPlanGML/5.0.BetaV2/Schema/Codelists/xplan_XP_MimeTypes.xml"
      >application/pdf</xplan:referenzMimeType>
     <xplan:typ>1030</xplan:typ>
    </xplan:XP_SpezExterneReferenz>
   </xplan:externeReferenz>
   <xplan:texte xlink:href="#GML_7a907203-3b41-4064-80ac-eca8002076f7"/>
   <xplan:gemeinde>
    <xplan:XP_Gemeinde>
     <xplan:ags>1234567</xplan:ags>
     <xplan:gemeindeName>Demo-Gemeinde</xplan:gemeindeName>
    </xplan:XP_Gemeinde>
   </xplan:gemeinde>
   <xplan:planArt>1000</xplan:planArt>
   <xplan:verfahren>2000</xplan:verfahren>
   <xplan:rechtsstand>4000</xplan:rechtsstand>
   <xplan:inkrafttretensDatum>2006-09-01</xplan:inkrafttretensDatum>
   <xplan:staedtebaulicherVertrag>false</xplan:staedtebaulicherVertrag>
   <xplan:erschliessungsVertrag>false</xplan:erschliessungsVertrag>
   <xplan:durchfuehrungsVertrag>false</xplan:durchfuehrungsVertrag>
   <xplan:bereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
  </xplan:BP_Plan>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_StrassenVerkehrsFlaeche gml:id="GML_5ed7abfd-7ccd-41a7-bbb2-3a8e3ba28735">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954752.164 5499860.173</gml:lowerCorner>
     <gml:upperCorner>3954775.589 5499970.567</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_5e3f19ae-85a0-412a-a3f2-5318517bda04">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="25">3954767.609 5499918.603 3954767.608 5499923.714
        3954767.319 5499945.964 3954767.584 5499960.028 3954769.026 5499964.03 3954771.438
        5499966.536 3954775.589 5499967.563 3954766.808 5499968.854 3954760.469 5499969.785
        3954755.146 5499970.567 3954757.919 5499969.393 3954759.869 5499967.046 3954760.581
        5499964.154 3954760.613 5499924.935 3954760.616 5499917.469 3954760.628 5499900.031
        3954759.349 5499888.134 3954756.426 5499876.55 3954752.164 5499865.665 3954754.756
        5499862.867 3954757.256 5499860.173 3954761.394 5499869.122 3954764.285 5499878.571
        3954767.624 5499897.422 3954767.609 5499918.603 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>true</xplan:flaechenschluss>
  </xplan:BP_StrassenVerkehrsFlaeche>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_UnverbindlicheVormerkung gml:id="GML_387ba2c0-d7e5-401d-a298-584375407531">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954739.16 5499930.837</gml:lowerCorner>
     <gml:upperCorner>3954739.16 5499930.837</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>4000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Point srsName="EPSG:31467" gml:id="GML_303b7195-ef09-40b6-af33-6e5494a56b10">
     <gml:pos>3954739.16 5499930.837</gml:pos>
    </gml:Point>
   </xplan:position>
   <xplan:flaechenschluss>false</xplan:flaechenschluss>
   <xplan:vormerkung>Hier soll einmal ein Baum gepflanzt werden</xplan:vormerkung>
  </xplan:BP_UnverbindlicheVormerkung>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_VerEntsorgung gml:id="GML_ee9b3332-16db-4f62-951a-c26587830255">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954697.404 5499903.941</gml:lowerCorner>
     <gml:upperCorner>3954697.404 5499903.941</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Point srsName="EPSG:31467" gml:id="GML_867922fc-73c9-40f5-8f26-837aefbdc187">
     <gml:pos>3954697.404 5499903.941</gml:pos>
    </gml:Point>
   </xplan:position>
   <xplan:flaechenschluss>false</xplan:flaechenschluss>
   <xplan:zweckbestimmung>10003</xplan:zweckbestimmung>
  </xplan:BP_VerEntsorgung>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:SO_Denkmalschutzrecht gml:id="GML_4251b3b6-9e6b-4075-a72a-b09131fabb87">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954773.194 5499928.438</gml:lowerCorner>
     <gml:upperCorner>3954788.525 5499961.052</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:ebene>0</xplan:ebene>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:rechtscharakter>2000</xplan:rechtscharakter>
   <xplan:position>
    <gml:Polygon srsName="EPSG:31467" gml:id="GML_b554c46a-4e6e-47a8-abc2-2d5f39500e48">
     <gml:exterior>
      <gml:LinearRing>
       <gml:posList srsDimension="2" count="10">3954773.194 5499960.939 3954773.22 5499957.945
        3954774.786 5499952.441 3954778.01 5499946.021 3954778.017 5499928.438 3954788.525
        5499928.483 3954788.446 5499946.037 3954786.552 5499950.953 3954786.469 5499961.052
        3954773.194 5499960.939 </gml:posList>
      </gml:LinearRing>
     </gml:exterior>
    </gml:Polygon>
   </xplan:position>
   <xplan:flaechenschluss>false</xplan:flaechenschluss>
   <xplan:artDerFestlegung>1000</xplan:artDerFestlegung>
   <xplan:detailArtDerFestlegung
    codeSpace="http://www.xplanungwiki.de/upload/XPlanGML/5.0.BetaV2/Schema/Codelists/xplan_SO_DetailKlassifizNachDenkmalschutzrecht.xml"
    >1000</xplan:detailArtDerFestlegung>
   <xplan:weltkulturerbe>false</xplan:weltkulturerbe>
  </xplan:SO_Denkmalschutzrecht>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:XP_PPO gml:id="GML_b4e47d29-d21c-42ab-85b7-b12ea57e89f2">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954699.5 5499942.75</gml:lowerCorner>
     <gml:upperCorner>3954699.5 5499942.75</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:stylesheetId>Default</xplan:stylesheetId>
   <xplan:art>besondereArtDerBaulNutzung</xplan:art>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:dientZurDarstellungVon xlink:href="#GML_013e660f-385d-4338-b9f2-85f30845ec26"/>
   <xplan:position>
    <gml:Point srsName="EPSG:31467" gml:id="GML_8463e155-c897-4261-9fd4-e7baa38a94c6">
     <gml:pos>3954699.5 5499942.75</gml:pos>
    </gml:Point>
   </xplan:position>
   <xplan:drehwinkel uom="deg">0</xplan:drehwinkel>
   <xplan:skalierung>1</xplan:skalierung>
  </xplan:XP_PPO>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:XP_PPO gml:id="GML_f740a6e7-2813-4392-a050-e744872125c6">
   <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
     <gml:lowerCorner>3954739.5 5499946.5</gml:lowerCorner>
     <gml:upperCorner>3954739.5 5499946.5</gml:upperCorner>
    </gml:Envelope>
   </gml:boundedBy>
   <xplan:stylesheetId>Default</xplan:stylesheetId>
   <xplan:art>besondereArtDerBaulNutzung</xplan:art>
   <xplan:gehoertZuBereich xlink:href="#GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"/>
   <xplan:dientZurDarstellungVon xlink:href="#GML_e346d550-1247-4012-91f4-df3f4f5d3b1a"/>
   <xplan:position>
    <gml:Point srsName="EPSG:31467" gml:id="GML_2d47b71b-a7b1-40a5-8ec3-1cdf51d543eb">
     <gml:pos>3954739.5 5499946.5</gml:pos>
    </gml:Point>
   </xplan:position>
   <xplan:drehwinkel uom="deg">0</xplan:drehwinkel>
   <xplan:skalierung>1</xplan:skalierung>
  </xplan:XP_PPO>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_TextAbschnitt gml:id="GML_7a907203-3b41-4064-80ac-eca8002076f7">
   <xplan:refText>
    <xplan:XP_ExterneReferenz>
     <xplan:art>Dokument</xplan:art>
     <xplan:referenzURL>http://www.Demogemeinde.de/BPlaene/DemoplanTextlicheFestsetzungen.pdf</xplan:referenzURL>
     <xplan:beschreibung>Zusammenstellung der textlichen Festsetzungen</xplan:beschreibung>
    </xplan:XP_ExterneReferenz>
   </xplan:refText>
   <xplan:rechtscharakter>9998</xplan:rechtscharakter>
  </xplan:BP_TextAbschnitt>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_TextAbschnitt gml:id="NeueTextlicheFestsetzung">
   <xplan:text>Diese Textliche Festsetzung gilt seit 1.4.2007 auch noch im Bereich des BPlan
    Demo-Gemeinde</xplan:text>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
  </xplan:BP_TextAbschnitt>
 </gml:featureMember>
 <gml:featureMember>
  <xplan:BP_TextAbschnitt gml:id="TextAbschnitt_A">
   <xplan:text>Hier soll ein Kindergarten entstehen</xplan:text>
   <xplan:rechtscharakter>1000</xplan:rechtscharakter>
  </xplan:BP_TextAbschnitt>
 </gml:featureMember>
</xplan:XPlanAuszug>
