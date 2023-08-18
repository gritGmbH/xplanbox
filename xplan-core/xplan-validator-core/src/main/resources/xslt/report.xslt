<?xml version="1.0" encoding="UTF-8"?>
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

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output
            method="xhtml" omit-xml-declaration="yes" indent="yes" />
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
                <style type="text/css">
                    body{
                        font-family:Arial;
                        }
                </style>
                <xsl:text disable-output-escaping="yes">

      &lt;script type="text/javascript"&gt;
                    /* &lt;![CDATA[ */

                  function hideOrShow(hideOrShowElementId, clickElement) {
                      var elementToHideShow = document.getElementById(hideOrShowElementId);
                      if (elementToHideShow.style.display=="block") {
                        elementToHideShow.style.display="none"
                        clickElement.innerHTML = "anzeigen";
                      } else {
                        elementToHideShow.style.display="block"
                        clickElement.innerHTML = "ausblenden";
                      }
                  }
                  function hideOrShowByClass(hideOrShowClass, clickElement) {
                    var elementsToHideShow = document.getElementsByClassName(hideOrShowClass);
                    for(var i = 0; i &lt; elementsToHideShow.length; ++i) {
                      var elementToHideShow = elementsToHideShow[i];
                      if (elementToHideShow.style.display=="none") {
                        elementToHideShow.style.display="";
                        clickElement.innerHTML = "ausblenden";
                      } else {
                        elementToHideShow.style.display="none"
                        clickElement.innerHTML = "anzeigen";
                      }
                    }
                  }
                  /* ]]&gt; */
      &lt;/script&gt;
                </xsl:text>
            </head>
            <body>
                <h1>Validierungsbericht</h1>
                <p>Name:
                    <b>
                        <xsl:value-of select="ValidationReport/name"/>
                    </b>
                </p>
                <p>XPlan Archivname:
                    <b>
                        <xsl:value-of select="ValidationReport/fileName"/>
                    </b>
                </p>
                <p>Datum:
                    <b>
                        <xsl:call-template name="format-date">
                            <xsl:with-param name="date">
                                <xsl:value-of select="ValidationReport/date"/>
                            </xsl:with-param>
                        </xsl:call-template>
                    </b>
                </p>
                <p>Ergebnis:
                    <b>
                        <xsl:choose>
                            <xsl:when test="ValidationReport/isValid='true'">
                                <font color="#00C000">valide</font>
                            </xsl:when>
                            <xsl:otherwise>
                                <font color="#FF0000">nicht valide</font>
                            </xsl:otherwise>
                        </xsl:choose>
                    </b>
                </p>
                <p>XPlanGML Version:
                  <b>
                    <xsl:value-of select="ValidationReport/Plan/version"/>
                  </b>
                </p>
                <p>Plannamen:
                  <b>
                    <ul>
                      <xsl:for-each select="ValidationReport/Plan/name">
                        <li>
                          <xsl:value-of select="."/>
                        </li>
                      </xsl:for-each>
                    </ul>
                  </b>
                </p>
                <xsl:apply-templates select="ValidationReport/ExternalReferences"/>
                <hr/>
                <xsl:apply-templates select="ValidationReport/Validation/*"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="ValidationReport/ExternalReferences">
      <p>Externe Referenzen:
        <b>
          <xsl:choose>
            <xsl:when test="SkipMessage">
              <xsl:value-of select="SkipMessage"/>
            </xsl:when>
            <xsl:otherwise>
              <ul>
                <xsl:for-each select="ExternalReference">
                  <li>
                    <xsl:choose>
                      <xsl:when test="@status='available'">
                        <xsl:value-of select="."/> (<font color="#00C000">vorhanden</font>)
                      </xsl:when>
                      <xsl:when test="@status='missing'">
                        <xsl:value-of select="."/> (<font color="#FF0000">fehlt</font>)
                      </xsl:when>
                      <xsl:when test="@status='unchecked'">
                        <xsl:value-of select="."/> (<font color="#CCCC33">nicht geprüft</font>)
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:value-of select="."/>
                      </xsl:otherwise>
                    </xsl:choose>
                  </li>
                </xsl:for-each>
              </ul>
            </xsl:otherwise>
          </xsl:choose>
        </b>
      </p>
    </xsl:template>

    <xsl:template match="ValidationReport/Validation/Sem | ValidationReport/Validation/Geom | ValidationReport/Validation/Syn">
        <p>
            Ergebnis der
            <xsl:choose>
                <xsl:when test="local-name()='Sem'">
                    semantischen
                </xsl:when>
                <xsl:when test="local-name()='Geom'">
                    geometrischen
                </xsl:when>
                <xsl:when test="local-name()='Syn'">
                    syntaktischen
                </xsl:when>
            </xsl:choose>
            Validierung:
            <i>
                <xsl:value-of select="result"/>
            </i>
            <div>
              <xsl:call-template name="tokenize">
                <xsl:with-param name="string" select="normalize-space(details)"/>
              </xsl:call-template>
            </div>
            <xsl:apply-templates select="RulesMetadata"/>
            <xsl:apply-templates select="Messages | Warnings | Errors"/>
            <xsl:apply-templates select="Rules"/>
            <hr/>
        </p>
    </xsl:template>

    <xsl:template match="ValidationReport/Validation/Profile">
      <p>
        Ergebnis der Validierung gegen das Profil "<xsl:value-of select="RulesMetadata/name"/>":
        <i>
          <xsl:value-of select="result"/>
        </i>
        <div>
          <xsl:value-of select="RulesMetadata/description"/>
        </div>
        <div>
          <xsl:call-template name="tokenize">
            <xsl:with-param name="string" select="normalize-space(details)"/>
          </xsl:call-template>
        </div>
        <xsl:apply-templates select="RulesMetadata"/>
        <xsl:apply-templates select="Rules">
          <xsl:with-param name="rulesPosition" select="position()" tunnel="yes"/>
        </xsl:apply-templates>
        <hr/>
      </p>
    </xsl:template>

    <xsl:template match="RulesMetadata">
      <div>Informationen zu den Regeln:</div>
      <p>Version: <xsl:value-of select="version"/></p>
      <p>Quelle:
        <xsl:call-template name="tokenize">
          <xsl:with-param name="string" select="normalize-space(source)"/>
        </xsl:call-template>
      </p>
    </xsl:template>

    <xsl:template match="Rules">
        <xsl:param name="rulesPosition" tunnel="yes"/>
        <xsl:variable name="apos" select='"&apos;"'/>
        <xsl:if test="*">
            <p>
                Zusammenfassung
                <ul>
                    <li><xsl:value-of select="count(./Rule)" /> Validierungsregeln überprüft</li>
                    <li><xsl:value-of select="count(./Rule[isValid='false'])" /> Validierungsregeln nicht erfüllt</li>
                    <li><xsl:value-of select="count(./Rule[isValid='true'])" /> Validierungsregeln erfüllt (
                      <xsl:element name="span">
                        <xsl:attribute name="style">text-decoration: underline; cursor:pointer</xsl:attribute>
                        <xsl:attribute name="onclick">
                          <xsl:value-of select="concat('javascript:hideOrShowByClass(', $apos, 'validSemanticRule_', $rulesPosition, $apos, ' , this); return false;')"/>
                        </xsl:attribute>
                        anzeigen
                      </xsl:element> )
                    </li>
                </ul>
                <table border="1">
                    <tr>
                        <th>Regel</th>
                        <th>Status</th>
                        <th>Beschreibung</th>
                        <th>GML Ids</th>
                    </tr>
                    <xsl:for-each select="*">
                        <xsl:choose>
                            <xsl:when test="not(current()/WarnedFeatures or current()/ErroredFeatures)">
                                <xsl:element name="tr">
                                    <xsl:attribute name="class">
                                      <xsl:value-of select="concat('validSemanticRule_', $rulesPosition)"/>
                                    </xsl:attribute>
                                    <xsl:attribute name="style">display:none</xsl:attribute>
                                  <td>
                                    <xsl:value-of select="current()/name"/>
                                  </td>
                                  <td>
                                    <font color="#00C000">erfüllt</font>
                                  </td>
                                  <td>
                                    <xsl:value-of select="current()/message"/>
                                  </td>
                                  <td>-</td>
                                </xsl:element>
                          </xsl:when>
                          <xsl:otherwise>
                            <xsl:for-each select="current()/WarnedFeatures">
                              <xsl:call-template name="WarnedOrErroredFeature">
                                <xsl:with-param name="type" >WARNING</xsl:with-param>
                                <xsl:with-param name="warnedOrErroredFeature" select="."/>
                              </xsl:call-template>
                            </xsl:for-each>
                            <xsl:for-each select="current()/ErroredFeatures">
                              <xsl:call-template name="WarnedOrErroredFeature">
                                <xsl:with-param name="type" >ERROR</xsl:with-param>
                                <xsl:with-param name="warnedOrErroredFeature" select="."/>
                              </xsl:call-template>
                            </xsl:for-each>
                          </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </table>
            </p>
        </xsl:if>
    </xsl:template>

    <xsl:template name="WarnedOrErroredFeature">
      <xsl:param name="type" />
      <xsl:param name="warnedOrErroredFeature" />
        <tr>
          <td>
            <xsl:value-of select="../name"/>
          </td>
          <xsl:choose>
            <xsl:when test="$type='WARNING'">
              <td>
                <font color="#FFC300">Warnung</font>
              </td>
            </xsl:when>
            <xsl:when test="$type='ERROR'">
              <td>
                <font color="#FF0000">nicht erfüllt</font>
              </td>
            </xsl:when>
            <xsl:otherwise>
              <td></td>
            </xsl:otherwise>
          </xsl:choose>
          <td>
            <xsl:value-of select="$warnedOrErroredFeature/message"/>
          </td>
          <td>
            <xsl:choose>
              <xsl:when test="not($warnedOrErroredFeature/gmlid)">
                -
              </xsl:when>
              <xsl:otherwise>
                <xsl:for-each select="$warnedOrErroredFeature/gmlid">
                  <xsl:if test="position() != 1">
                    <xsl:text>, </xsl:text>
                  </xsl:if>
                  <xsl:value-of select="."/>
                </xsl:for-each>
              </xsl:otherwise>
            </xsl:choose>
          </td>
        </tr>
    </xsl:template>

    <xsl:template match="Messages | Warnings | Errors">
        <xsl:if test="*">
            <p>
                <xsl:choose>
                    <xsl:when test="local-name()='Messages'">
                        Benachrichtigungen
                    </xsl:when>
                    <xsl:when test="local-name()='Warnings'">
                        <xsl:value-of select="count(./warning)" /> Warnungen (<span style="text-decoration: underline; cursor:pointer" onclick="javascript:hideOrShow('WarningsDetails', this); return false;">ausblenden</span>)
                    </xsl:when>
                    <xsl:when test="local-name()='Errors'">
                        Fehler
                    </xsl:when>
                </xsl:choose>
                <xsl:element name="table">
                  <xsl:attribute name="border">1</xsl:attribute>
                  <xsl:attribute name="id"><xsl:value-of select="local-name()" />Details</xsl:attribute>
                  <xsl:if test="local-name()='Warnings'">
                    <xsl:attribute name="style">display:block</xsl:attribute>
                  </xsl:if>
                  <xsl:for-each select="*">
                    <tr>
                      <td>
                        <xsl:value-of select="current()" />
                      </td>
                    </tr>
                  </xsl:for-each>
                </xsl:element>
            </p>
        </xsl:if>
    </xsl:template>

  <xsl:template name="tokenize">
    <xsl:param name="string" />
    <xsl:choose>
      <xsl:when test="contains($string,' ')">
        <xsl:value-of select="substring-before($string,' ')" />
        <xsl:text> </xsl:text>
        <xsl:call-template name="tokenize">
          <xsl:with-param name="string" select="substring-after($string,' ')" />
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="starts-with($string,'http')">
            <xsl:element name="a">
              <xsl:attribute name="href"><xsl:value-of select="$string"></xsl:value-of> </xsl:attribute>
              <xsl:attribute name="target">_blank</xsl:attribute>
              <xsl:value-of select="$string"></xsl:value-of>
            </xsl:element>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$string" />
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

    <xsl:template name="format-date">
        <xsl:param name="date" />
        <!-- input format yyyy-MM-DDThh:mm:s -->
        <!-- Example: 2019-09-19T06:56:29.650Z -->
        <!-- output format DD.MM.yyyy hh:mm -->
        <!-- Example: 19.09.2019 06:56 -->

        <xsl:variable name="year">
            <xsl:value-of select="substring($date,1,4)" />
        </xsl:variable>

        <xsl:variable name="month">
            <xsl:value-of select="substring($date,6,2)" />
        </xsl:variable>

        <xsl:variable name="day">
            <xsl:value-of select="substring($date,9,2)" />
        </xsl:variable>

        <xsl:variable name="hour">
            <xsl:value-of select="substring($date,12,2)" />
        </xsl:variable>

        <xsl:variable name="minute">
            <xsl:value-of select="substring($date,15,2)" />
        </xsl:variable>

        <xsl:value-of select="concat($day, '.', $month, '.', $year, ' ', $hour, ':', $minute)" />
    </xsl:template>

</xsl:stylesheet>
