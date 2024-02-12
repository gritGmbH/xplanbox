package de.latlon.xplanbox.cli.manage.converter;

import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import picocli.CommandLine;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CrsConverter implements CommandLine.ITypeConverter<ICRS> {

	@Override
	public ICRS convert(String crs) {
		try {
			CRSManager.lookup(crs);
			return CRSManager.getCRSRef(crs);
		}
		catch (UnknownCRSException e) {
			throw new CommandLine.TypeConversionException("Das angegebene CRS '" + crs + "' ist unbekannt.");
		}
	}

}