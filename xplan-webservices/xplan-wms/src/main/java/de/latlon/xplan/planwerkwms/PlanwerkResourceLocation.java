/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.planwerkwms;

import org.apache.commons.io.IOUtils;
import org.deegree.services.OWS;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.ResourceException;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.standard.DefaultResourceIdentifier;
import org.deegree.workspace.standard.DefaultResourceLocation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * {@link ResourceLocation} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkResourceLocation extends DefaultResourceLocation<OWS> {

	private byte[] bytes;

	private final WmsMetadata wmsMetadata;

	public PlanwerkResourceLocation(byte[] bytes, DefaultResourceIdentifier<OWS> identifier, WmsMetadata wmsMetadata) {
		super(null, identifier);
		this.bytes = bytes;
		this.wmsMetadata = wmsMetadata;
	}

	@Override
	public InputStream getAsStream() {
		return new ByteArrayInputStream(bytes);
	}

	@Override
	public InputStream resolve(String path) {
		return wmsMetadata.getLocation().resolve(path);
	}

	@Override
	public File resolveToFile(String path) {
		return wmsMetadata.getLocation().resolveToFile(path);
	}

	@Override
	public URL resolveToUrl(String path) {
		return wmsMetadata.getLocation().resolveToUrl(path);
	}

	@Override
	public void deactivate() {
		wmsMetadata.getLocation().deactivate();
	}

	@Override
	public void activate() {
		wmsMetadata.getLocation().activate();
	}

	@Override
	public void setContent(InputStream in) {
		try {
			bytes = IOUtils.toByteArray(in);
		}
		catch (IOException e) {
			throw new ResourceException(e.getLocalizedMessage(), e);
		}
	}

}
