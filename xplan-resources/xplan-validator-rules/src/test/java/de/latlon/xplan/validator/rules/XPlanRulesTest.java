package de.latlon.xplan.validator.rules;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.validator.rules.XPlanRules.retrieveInternalRulesPath;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.walkFileTree;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        final List<Path> filesCompiled = new ArrayList<>();
        long before = System.currentTimeMillis();
        walkFileTree( retrieveInternalRulesPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile( Path file, BasicFileAttributes attrs ) {
                if ( file.toString().endsWith( ".xq" ) ) {
                    try {
                        filesCompiled.add( file );
                        compileStatement( newInputStream( file ) );
                    } catch ( Exception e ) {
                        filesInError.add( file );
                        System.out.println( "Failed to compile statement " + file );
                    }
                }
                return CONTINUE;
            }
        } );

        long after = System.currentTimeMillis();
        System.out.println( "Compiled " + filesCompiled.size() + " in " + ( ( after - before ) / 1000 ) + " s." );
        assertThat( filesInError.size(), is( 0 ) );
    }

    // used for devlopment - #5844
    // use the option -Xss 356k (e.g.) to reproduce the StackOverflowError
    @Ignore
    @Test
    public void checkRule()
                    throws Exception {
        for ( int i = 1; i <= 100; i++ ) {
            System.out.println( "Run: " + i );
            Path path = retrieveInternalRulesPath().resolve( "xplangml51/xp/3.1.3.4.xq" );
            compileStatement( newInputStream( path ) );
        }
    }

    private XQueryExpression compileStatement( InputStream statementStream )
                    throws Exception {
        final StaticQueryContext staticQueryContext = configuration.newStaticQueryContext();
        return staticQueryContext.compileQuery( statementStream, "UTF-8" );
    }
}
