package de.latlon.xplan.validator.report.pdf;

import net.sf.dynamicreports.report.builder.MarginBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Contains styles and useful components
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class Templates {

    static final StyleBuilder simpleStyle;

    private static final StyleBuilder boldStyle;

    private static final StyleBuilder boldCenterStyle;

    static final StyleBuilder root20LeftIndentStyle;

    static final StyleBuilder bold14LeftStyle;

    static final StyleBuilder bold18LeftStyle;

    private static final StyleBuilder bold22LeftStyle;

    static {
        simpleStyle = stl.style().setPadding( 2 );
        root20LeftIndentStyle = stl.style( simpleStyle ).setLeftIndent( 20 );
        boldStyle = stl.style( simpleStyle ).bold();
        StyleBuilder boldLeftStyle = stl.style( boldStyle ).setAlignment( HorizontalAlignment.LEFT,
                                                                          VerticalAlignment.MIDDLE );
        boldCenterStyle = stl.style( boldStyle ).setAlignment( HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE );
        bold14LeftStyle = stl.style( boldLeftStyle ).setFontSize( 14 );
        bold18LeftStyle = stl.style( boldLeftStyle ).setFontSize( 18 );
        bold22LeftStyle = stl.style( boldLeftStyle ).setFontSize( 22 );
    }

    /**
     * @return the template, never <code>null</code>
     */
    static ReportTemplateBuilder createTemplate() {
        TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer().
              setHeadingStyle( 0, boldStyle );
        MarginBuilder pargeMargin = margin().setLeft( 30 ).setRight( 30 ).setTop( 30 ).setBottom( 30 );
        return template().setLocale( Locale.GERMAN ).setPageMargin( pargeMargin ).highlightDetailEvenRows()
              .crosstabHighlightEvenRows().setTableOfContentsCustomizer( tableOfContentsCustomizer );
    }

    /**
     * Create a title component
     *
     * @param title the title
     * @return the title component, never <code>null</code>
     */
    static ComponentBuilder<?, ?> createTitleComponent( String title ) {
        return cmp.horizontalList().add( cmp.verticalList( cmp.text( title ).setStyle( bold22LeftStyle ) ) ).newRow()
              .add( cmp.line() ).newRow().add( cmp.verticalGap( 10 ) );
    }

    /**
     * Creates a footer component with page number
     *
     * @return the footer component, never <code>null</code>
     */
    static ComponentBuilder<?, ?> createFooter() {
        return cmp.pageXslashY().setStyle( stl.style( boldCenterStyle ).setTopBorder( stl.pen1Point() ) );
    }

}