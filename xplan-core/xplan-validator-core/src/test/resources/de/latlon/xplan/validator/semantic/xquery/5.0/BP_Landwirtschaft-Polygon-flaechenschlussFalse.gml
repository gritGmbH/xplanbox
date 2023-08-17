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

<xplan:XPlanAuszug xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.xplanung.de/xplangml/5/0 ../../../../../../../../../../../xplan-schemas/src/main/resources/appschemas/XPlanGML_5_0/XPlanung-Operationen.xsd"
                   xmlns:xplan="http://www.xplanung.de/xplangml/5/0"
                   xmlns:xlink="http://www.w3.org/1999/xlink"
                   xmlns:gml="http://www.opengis.net/gml/3.2"
                   gml:id="GML_40adb0a5-8ba6-478e-8384-c1939d2711c7">
  <gml:boundedBy>
    <gml:Envelope srsName="EPSG:31467">
      <gml:lowerCorner>3954633.369 5499860.173</gml:lowerCorner>
      <gml:upperCorner>3954791.49 5499972.787</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <gml:featureMember>
    <xplan:BP_Landwirtschaft gml:id="GML_1265b858-4bfa-4694-94dd-f3b3cbd9ee03">
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
      <xplan:flaechenschluss>false</xplan:flaechenschluss>
    </xplan:BP_Landwirtschaft>
  </gml:featureMember>
</xplan:XPlanAuszug>
