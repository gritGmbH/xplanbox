package de.latlon.xplan.commons.hale;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates an ImportReader or ImportWriter with settings.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleIOProvider {

    private final String name;

    private final Map<String, String> settings = new HashMap<>();

    /**
     * @param name
     *                 the name of the ImportReader or ImportWriter (ID-of-target-writer), never <code>null</code>
     */
    public HaleIOProvider( String name ) {
        this.name = name;
    }

    /**
     * Adds a new setting
     *
     * @param key
     *                 of the setting, never <code>null</code>
     * @param value
     *                 of the setting, never <code>null</code>
     */
    public void addSetting( String key, String value ) {
        settings.put( key, value );
    }

    /**
     * @return the name of the ImportReader or ImportWriter (ID-of-target-writer), never <code>null</code>
     */
    public String getName() {
        return name;
    }

    /**
     * @return additional settings, may be empty but never <code>null</code>
     */
    public Map<String, String> getSettings() {
        return settings;
    }

}
