<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="entryes">
        <entryes>
            <xsl:for-each select="entry/fields">
                <entry>
                    <xsl:attribute name="field">
                        <xsl:value-of select="value">
                        </xsl:value-of>
                    </xsl:attribute>
                </entry>
            </xsl:for-each>
        </entryes>
    </xsl:template>
</xsl:stylesheet>