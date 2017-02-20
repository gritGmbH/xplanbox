package de.latlon.xplan.manager.web.client.gui.dialog;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapPreviewDialogIT {

    private static List<WebDriver> drivers = new ArrayList<>();

    @BeforeClass
    public static void openBrowser() {
        WebDriver firefoxDriver = new HtmlUnitDriver( BrowserVersion.FIREFOX_24 );
        WebDriver chromeDriver = new HtmlUnitDriver( BrowserVersion.CHROME );
        WebDriver ieDriver = new HtmlUnitDriver( BrowserVersion.INTERNET_EXPLORER_11 );
        drivers.add( firefoxDriver );
        drivers.add( chromeDriver );
        drivers.add( ieDriver );
        for ( WebDriver driver : drivers ) {
            ( (HtmlUnitDriver) driver ).setJavascriptEnabled( true );
            String url = System.getProperty( "url" );
            String contextPath = System.getProperty( "managerWebContextPath" );
            driver.get( url + contextPath );
        }
    }

    @AfterClass
    public static void quitDriver()
                    throws IOException {
        for ( WebDriver driver : drivers ) {
            driver.quit();
        }
    }

    @Ignore("Map preview button cannot be localized as it does not have the title Kartenvorschau anymore.")
    @Test
    public void urlRequests()
                    throws IOException {
        for ( WebDriver driver : drivers ) {
            String[] url = { "http://ows.terrestris.de/osm/service?", "http://localhost:8080/xplan-wms/services/wms?" };

            WebDriverWait wait = new WebDriverWait( driver, 90 );
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath( "//button[.='Kartenvorschau']" ) ) );
            WebElement elem = driver.findElement( By.xpath( "//button[.='Kartenvorschau']" ) );
            elem.click();

            for ( String winHandle : driver.getWindowHandles() ) {
                driver.switchTo().window( winHandle );
            }

            List<WebElement> listImg = driver.findElements( By.className( "olTileImage" ) );
            java.util.Iterator<WebElement> k = listImg.iterator();
            while ( k.hasNext() ) {
                WebElement img = k.next();
                String src = img.getAttribute( "src" );
                boolean match = false;
                for ( int j = 0; j < url.length; j++ ) {
                    if ( src.startsWith( url[j] ) ) {
                        match = true;
                    }
                }
                Assert.assertTrue( "The remote url " + src + " is wrong", match );
            }
        }
    }
}
