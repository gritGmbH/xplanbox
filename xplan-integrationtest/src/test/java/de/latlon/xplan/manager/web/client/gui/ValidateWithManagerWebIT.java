package de.latlon.xplan.manager.web.client.gui;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ValidateWithManagerWebIT {

    private String baseDir;

    private String fullUrl;

    private List<WebDriver> drivers;

    @Before
    public void openDriver() {
        baseDir = System.getProperty( "baseDir" );
        String url = System.getProperty( "url" );
        String contextPath = System.getProperty( "managerWebContextPath" );
        fullUrl = url + contextPath;
        drivers = new ArrayList<>();
        WebDriver firefoxDriver = new HtmlUnitDriver( BrowserVersion.FIREFOX_24 );
        WebDriver chromeDriver = new HtmlUnitDriver( BrowserVersion.CHROME );
        WebDriver ieDriver = new HtmlUnitDriver( BrowserVersion.INTERNET_EXPLORER_11 );
        drivers.add( firefoxDriver );
        drivers.add( chromeDriver );
        drivers.add( ieDriver );
        for ( WebDriver driver : drivers ) {
            ( (HtmlUnitDriver) driver ).setJavascriptEnabled( true );
            driver.manage().timeouts().implicitlyWait(30, SECONDS);
        }
    }

    @After
    public void quitDriver()
                    throws Exception {
        for ( WebDriver driver : drivers ) {
            driver.quit();
        }
    }

    @Test
    public void testValidatePlanWithEidelstedt_4_V4ShouldNotFail()
                    throws Exception {
        for ( WebDriver driver : drivers ) {
            driver.get( fullUrl );
            driver.findElement( By.name( "planZipFile" ) ).clear();
            String pathOfPlan = retrieveTestPlanPath( "xplan41/Eidelstedt_4_V4.zip" );
            driver.findElement( By.name( "planZipFile" ) ).sendKeys( pathOfPlan );
            driver.findElement( By.cssSelector( "button.stdFont" ) ).click();
            // TODO: htmlunit does not support handling of alerts so far. A solution has to be found how to continue.
            // driver.switchTo().alert().accept();
            // driver.findElement( By.cssSelector( "div > button[type=\"button\"]" ) ).click();
            // new Select( driver.findElement( By.cssSelector( "td > table > tbody > tr > td > select.gwt-ListBox" ) )
            // ).selectByVisibleText( "Semantische Validierung" );
            // driver.findElement( By.xpath( "(//button[@type='button'])[54]" ) ).click();
            // driver.findElement( By.xpath( "(//button[@type='button'])[57]" ) ).click();
        }
    }

    @Test
    public void testValidatePlanWithComplexCurveShouldNotFail()
                    throws Exception {
        for ( WebDriver driver : drivers ) {
            driver.get( fullUrl );
            driver.findElement( By.name( "planZipFile" ) ).clear();
            String pathOfPlan = retrieveTestPlanPath( "xplan41/PlanWithComplexCurve.zip" );
            driver.findElement( By.name( "planZipFile" ) ).sendKeys( pathOfPlan );
            driver.findElement( By.cssSelector( "button.stdFont" ) ).click();
            // TODO: htmlunit does not support handling of alerts so far. A solution has to be found how to continue.
            // driver.switchTo().alert().accept();
            // driver.findElement( By.cssSelector( "div > button[type=\"button\"]" ) ).click();
            // new Select( driver.findElement( By.cssSelector( "td > table > tbody > tr > td > select.gwt-ListBox" ) )
            // ).selectByVisibleText( "Semantische Validierung" );
            // driver.findElement( By.xpath( "(//button[@type='button'])[54]" ) ).click();
            // driver.findElement( By.xpath( "(//button[@type='button'])[57]" ) ).click();
        }
    }

    private String retrieveTestPlanPath( String name ) {
        return baseDir + "/xplan-test-resources/de/latlon/xplan/" + name;
    }

}