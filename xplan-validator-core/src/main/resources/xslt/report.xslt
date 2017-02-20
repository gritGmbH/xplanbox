<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output
            method="xml" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
                <style type="text/css">
                    body{
                        font-family:Arial;
                        }
                </style>
                <script>
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
                </script>
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
                        <xsl:value-of select="ValidationReport/Plan/name"/>
                    </b>
                </p>
                <hr/>
                <xsl:apply-templates select="ValidationReport/Validation/*"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="ValidationReport/Validation/*">
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
            <xsl:apply-templates select="Messages | Warnings | Errors"/>
            <xsl:apply-templates select="Rules"/>
            <hr/>
        </p>
    </xsl:template>

    <xsl:template match="Rules">
        <xsl:if test="*">
            <p>
                <table border="1">
                    <tr>
                        <th>Regel</th>
                        <th>Status</th>
                        <th>Beschreibung</th>
                    </tr>
                    <xsl:for-each select="*">
                        <tr>
                            <td>
                                <xsl:value-of select="current()/name"/>
                            </td>
                            <xsl:choose>
                                <xsl:when test="current()/isValid='true'">
                                    <td>
                                        <font color="#00C000">erfüllt</font>
                                    </td>
                                </xsl:when>
                                <xsl:when test="current()/isValid='false'">
                                    <td>
                                        <font color="#FF0000">nicht erfüllt</font>
                                    </td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td></td>
                                </xsl:otherwise>
                            </xsl:choose>
                            <td>
                                <xsl:value-of select="current()/message"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </p>
        </xsl:if>
    </xsl:template>

    <xsl:template match="Messages | Warnings | Errors">
        <xsl:if test="*">
            <p>
                <xsl:choose>
                    <xsl:when test="local-name()='Messages'">
                        Benachrichtigungen
                    </xsl:when>
                    <xsl:when test="local-name()='Warnings'">
                        <xsl:value-of select="count(./warning)" /> Warnungen <span style="color:red; cursor:pointer" onclick="javascript:hideOrShow('WarningsDetails', this); return false;">anzeigen</span>
                    </xsl:when>
                    <xsl:when test="local-name()='Errors'">
                        Fehler
                    </xsl:when>
                </xsl:choose>
                <xsl:element name="table">
                  <xsl:attribute name="border">1</xsl:attribute>
                  <xsl:attribute name="id"><xsl:value-of select="local-name()" />Details</xsl:attribute>
                  <xsl:if test="local-name()='Warnings'">
                    <xsl:attribute name="style">display:none</xsl:attribute>
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
          <xsl:when test="starts-with($string,'http://')">
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

</xsl:stylesheet>
