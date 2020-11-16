/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RulesMetadata {

    private @Valid
    String version;

    private @Valid
    String source;

    /**
     *
     **/
    public RulesMetadata version( String version ) {
        this.version = version;
        return this;
    }

    @Schema(example = "0.9.14")
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    /**
     *
     **/
    public RulesMetadata source( String source ) {
        this.source = source;
        return this;
    }

    @Schema(example = "https://bitbucket.org/geowerkstatt-hamburg/xplanung/get/v0.9.14.zip")
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    public void setSource( String source ) {
        this.source = source;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        RulesMetadata rulesMetadata = (RulesMetadata) o;
        return Objects.equals( this.version, rulesMetadata.version ) && Objects.equals( this.source,
                                                                                        rulesMetadata.source );
    }

    @Override
    public int hashCode() {
        return Objects.hash( version, source );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class RulesMetadata {\n" );

        sb.append( "    version: " ).append( toIndentedString( version ) ).append( "\n" );
        sb.append( "    source: " ).append( toIndentedString( source ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( java.lang.Object o ) {
        if ( o == null ) {
            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}

