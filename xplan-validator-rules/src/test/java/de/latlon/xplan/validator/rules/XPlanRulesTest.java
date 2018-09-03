package de.latlon.xplan.validator.rules;

import static de.latlon.xplan.validator.rules.XPlanRules.retrieveInternalRulesPath;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.walkFileTree;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;

import org.junit.Test;

/**
 * Checks if all xquery statements compile correctly
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XPlanRulesTest {

    private final Configuration configuration = new Configuration();

    @Test
    public void checkRules()
                            throws Exception {
        final List<Path> filesInError = new ArrayList<>();
        walkFileTree( retrieveInternalRulesPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile( Path file, BasicFileAttributes attrs ) {
                if ( file.toString().endsWith( ".xq" ) ) {
                    try {
                        compileStatement( newInputStream( file ) );
                    } catch ( Exception e ) {
                        filesInError.add( file );
                        System.out.println( "Failed to compile statement " + file );
                    }
                }
                return CONTINUE;
            }
        } );
        assertThat( filesInError.size(), is( 0 ) );
    }

    private XQueryExpression compileStatement( InputStream statementStream )
                            throws Exception {
        final StaticQueryContext staticQueryContext = configuration.newStaticQueryContext();
        return staticQueryContext.compileQuery( statementStream, "UTF-8" );
    }
}
