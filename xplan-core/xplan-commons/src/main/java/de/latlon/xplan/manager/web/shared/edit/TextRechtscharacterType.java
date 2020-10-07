package de.latlon.xplan.manager.web.shared.edit;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public enum TextRechtscharacterType {

    FESTSETZUNG( 1000 ),

    NACHRICHTLICHEUEBERNAHME( 2000 ),

    HINWEIS( 3000 ),

    VERMERK( 4000 ),

    KENNZEICHNUNG( 5000 ),

    UNBEKANNT( 9998 );

    private final int code;

    TextRechtscharacterType( int code ) {
        this.code = code;
    }

    public static TextRechtscharacterType fromCode( int code ) {
        for ( TextRechtscharacterType rechtscharacterType : values() ) {
            if ( rechtscharacterType.code == code )
                return rechtscharacterType;
        }
        throw new IllegalArgumentException( "Could not find rechtscharacter with code " + code );
    }

    public int getCode() {
        return code;
    }
}