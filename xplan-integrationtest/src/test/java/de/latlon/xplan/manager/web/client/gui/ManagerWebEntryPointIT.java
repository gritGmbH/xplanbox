package de.latlon.xplan.manager.web.client.gui;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerWebEntryPointIT {

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

    @Test
    public void pageTitle()
                    throws IOException {
        for ( WebDriver driver : drivers ) {
            Assert.assertEquals( "The page title should be XPlanManager", "XPlanManager", driver.getTitle() );
        }
    }
}
