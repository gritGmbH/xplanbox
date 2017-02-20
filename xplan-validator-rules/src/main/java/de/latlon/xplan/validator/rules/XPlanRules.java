package de.latlon.xplan.validator.rules;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Serves as target for easy retrieval of the rules path for unit testing purposes
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XPlanRules {

    private XPlanRules() {
    }

    /**
     * retrieves path to xplan rules
     * @return <code>Path</code> to xplan rules
     * @throws URISyntaxException
     */
    public static Path retrieveInternalRulesPath()
                            throws URISyntaxException {
        return Paths.get( XPlanRules.class.getClassLoader().getResource( "rules" ).toURI() );
    }
}
