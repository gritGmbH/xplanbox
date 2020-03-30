package de.latlon.xplanbox.internal.hale;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CreateClassificationScripts {

    private static final String NONE_ATTRIBUTE = "NONE";

    private static final String HILUCS_URL = "http://inspire.ec.europa.eu/codelist/HILUCSValue/";

    private static final String HILUCS_NOT_KNOWN = "6_6_NotKnownUse";

    private static final String SR_URL = "http://inspire.ec.europa.eu/codelist/SupplementaryRegulationValue/";

    private static final String SR_NOT_KNOWN = "10_OtherSupplementaryRegulation";

    public static void main( String[] args )
                            throws IOException {

        InputStream is = CreateClassificationScripts.class.getResourceAsStream(
                                "/xplanung/5_2/XPlanToINSPIRE-SupplementaryRegulation_2_2_2019-12-17.xlsx" );
        String urlPrefix = SR_URL;
        String notKnown = SR_NOT_KNOWN;

        Workbook workbook = WorkbookFactory.create( is );
        Sheet sheet = getSheetFoRPlanType( workbook, "BPlan" );

        Map<String, Classification> classifications = parseSheet( sheet );
        createDirectMappingScript( classifications, urlPrefix, notKnown );
        createClassificationScript( urlPrefix, classifications );

        workbook.close();
    }

    private static void createDirectMappingScript( Map<String, Classification> classifications, String urlPrefix,
                                                   String notKnown ) {
        StringBuilder sb = new StringBuilder();

        sb.append( "def featureTypeName = _snippets.classification {" ).append( "\n" );
        sb.append( "    featureTypeName(_sourceTypes)" ).append( "\n" );
        sb.append( "}" ).append( "\n" ).append( "\n" );

        sb.append( "def value = '" ).append( urlPrefix ).append( notKnown ).append( "';" ).append( "\n" ).append(
                                "\n" );

        boolean isFirst = true;
        for ( Map.Entry<String, Classification> class2Classification : classifications.entrySet() ) {
            Classification classification = class2Classification.getValue();

            if ( NONE_ATTRIBUTE.equals( classification.getXplanungAttribut() ) ) {
                String xplanungKlasse = classification.getXplanungKlasse();

                if ( !isFirst )
                    sb.append( " else " );
                sb.append( "if ( featureTypeName == '" ).append( xplanungKlasse ).append( "' ){" ).append( "\n" );

                List<String> values = classification.getDefaultClassification();
                values.forEach( ( codeValue ) -> {
                    sb.append( "    value = '" ).append( urlPrefix ).append( codeValue ).append( "';" ).append( "\n" );
                } );
                sb.append( "}" );
                isFirst = false;
            }
        }

        sb.append( "\n" ).append( "\n" );
        sb.append( "_target {" ).append( "\n" );
        sb.append( "    href ( value )" ).append( "\n" );
        sb.append( "}" ).append( "\n" );

        System.out.println( sb.toString() );
    }

    private static void createClassificationScript( String urlPrefix, Map<String, Classification> classifications ) {
        AtomicInteger index = new AtomicInteger( 1 );
        classifications.forEach( ( s, classification ) -> {
            createClassificationScript( s, classification, urlPrefix, index );
        } );
    }

    private static void createClassificationScript( String s, Classification classification, String urlPrefix,
                                                    AtomicInteger index ) {
        StringBuilder sb = new StringBuilder();
        if ( NONE_ATTRIBUTE.equals( classification.getXplanungAttribut() ) ) {
            return;
        }

        Map<Integer, List<String>> values = classification.getClassification();
        values.forEach( ( code, codeValues ) -> {
            if ( sb.length() > 0 )
                sb.append( " else " );
            sb.append( "if ( " ).append( classification.getXplanungAttribut() ).append( " == '" ).append( code ).append(
                                    "' ) {" ).append( "\n" );
            Collections.sort( codeValues );
            codeValues.forEach( value -> {
                sb.append( "  _target{" ).append( "\n" );
                sb.append( "    href ( '" ).append( urlPrefix ).append( value ).append( "' ) " ).append( "\n" );
                sb.append( "  }" ).append( "\n" );
            } );
            sb.append( "}" );

        } );
        List<String> defaultClassification = classification.getDefaultClassification();
        if ( defaultClassification != null ) {
            sb.append( " else {" ).append( "\n" );
            defaultClassification.forEach( value -> {
                sb.append( "  _target{" ).append( "\n" );
                sb.append( "    href ( '" ).append( urlPrefix ).append( value ).append( "' ) " ).append( "\n" );
                sb.append( "  }" ).append( "\n" );
            } );
            sb.append( "}" );
        }

        System.out.println( "==> " + index.getAndIncrement() + ". " + s );
        System.out.println( sb.toString() );

    }

    private static Map<String, Classification> parseSheet( Sheet sheet ) {
        Map<String, Classification> classifications = new TreeMap<>();
        boolean isFirst = true;
        for ( Row row : sheet ) {
            if ( !isFirst ) {
                parseRow( classifications, row );
            }
            isFirst = false;
        }
        return classifications;
    }

    private static void parseRow( Map<String, Classification> classifications, Row row ) {
        String klasse = row.getCell( 0 ).getStringCellValue();
        String attribut = row.getCell( 1 ).getStringCellValue();
        double code = row.getCell( 2 ).getNumericCellValue();
        String value = row.getCell( 3 ).getStringCellValue();
        String specific = row.getCell( 4 ).getStringCellValue();
        if ( attribut == null || attribut.isEmpty() ) {
            attribut = NONE_ATTRIBUTE;
        }

        if ( !classifications.containsKey( klasse ) )
            classifications.put( klasse, new Classification( klasse ) );

        Classification classification = classifications.get( klasse );
        classification.setXplanungAttribut( attribut );
        classification.addClass( code, value, specific );
    }

    private static Sheet getSheetFoRPlanType( Workbook workbook, String type ) {
        for ( Sheet sheet : workbook ) {
            System.out.println( "=> " + sheet.getSheetName() );
            if ( sheet.getSheetName().contains( type ) )
                return sheet;
        }
        return null;
    }

}