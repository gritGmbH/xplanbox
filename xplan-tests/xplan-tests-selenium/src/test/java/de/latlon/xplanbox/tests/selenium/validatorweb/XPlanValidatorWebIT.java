package de.latlon.xplanbox.tests.selenium.validatorweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class XPlanValidatorWebIT {

	private static final String NAMEBPLAN_TEST = "Selenium_Test";

	private static final String NAMEBPLAN_ORIGINAL = "BPlan001_5-4";

	private static String connectUrl;

	@BeforeAll
	static void readSystemProperties() {
		String url = System.getProperty("baseUrlValidatorWeb", "https://xplanbox.lat-lon.de");
		String username = System.getProperty("username", null);
		String password = System.getProperty("password", null);
		connectUrl = createConnectUrl(url, username, password);
	}

	@Test
	void test_XPlanValidatorWeb_withValidPlan(@TempDir(cleanup = CleanupMode.ON_SUCCESS) final Path tmpDir)
			throws Exception {

		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		// chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", tmpDir.toString());
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		options.addArguments("--no-sandbox");
		options.addArguments("--ignore-certificate-errors");

		// options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		String myZIPFile = getClass().getResource("/" + NAMEBPLAN_ORIGINAL + ".zip").getFile().toString();

		try {
			driver.get(connectUrl);
			assertEquals("XPlanValidatorWeb", driver.getTitle());

			WebElement elemAdd = driver.findElement(By.name("uploadPlanItem"));
			elemAdd.sendKeys(myZIPFile);

			driver.findElement(By.xpath("//button[text()='Hochladen und Validierungsoptionen einstellen']")).click();
			verifyByXPath(driver, "//*[@class='gwt-DialogBox']//*[@class='Caption']", "Plan hochgeladen");

			// einmal abbrechen und neu validieren
			driver.findElement(By.xpath("//button[text()='abbrechen']")).click();

			driver.findElement(By.xpath("//button[text()='Hochladen und Validierungsoptionen einstellen']")).click();
			verifyByXPath(driver, "//*[@class='gwt-DialogBox']//*[@class='Caption']", "Plan hochgeladen");

			driver.findElement(By.xpath("//button[text()='zur Validierung']")).click();
			verifyByXPath(driver, "//*[@class='valOptionTitle']", "Validierungsoptionen");

			WebElement elemName = driver.findElement(By.xpath(
					"//tr[td/div[@class='valOptionLabel']/text() = 'Bezeichnung für den Report']/following-sibling::tr[1]//input"));
			elemName.clear();
			elemName.sendKeys(NAMEBPLAN_TEST);

			driver.findElement(By.xpath("//label[text() = 'semantisch']")).click();

			// Hier kann theoretisch noch ein Schema ausgewählt werden
			// try {
			// driver.findElement(By.xpath("//tr[12]/td/span/input")).click();
			// sleep();
			// }
			// catch (NoSuchElementException nSEE_Ex) {
			// nSEE_Ex.printStackTrace();
			// }

			driver.findElement(By.xpath("//button[text()='Validierung starten']")).click();

			verifyByXPath(driver, "//*[@class='gwt-DialogBox']//*[@class='Caption']", "Ergebnisse der Validierung", 30);

			WebElement iframe = driver.findElement(
					By.xpath("//html/body/div[4]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td[1]/iframe")); // Hier
																													// iframeName
																													// durch
																													// den
																													// tatsächlichen
																													// Namen
																													// des
																													// iframes
																													// ersetzen
			driver.switchTo().frame(iframe);

			String name = driver.findElement(By.xpath("//body/p[1]/b")).getText();
			String valide = driver.findElement(By.xpath("//body/p[4]/b/font")).getText();
			String planname = driver.findElement(By.xpath("//body/ul[1]/b/li[1]")).getText();

			SoftAssertions softly = new SoftAssertions();
			softly.assertThat(name).isEqualTo(NAMEBPLAN_TEST);
			softly.assertThat(valide).isEqualTo("valide");
			softly.assertThat(planname).isEqualTo(NAMEBPLAN_ORIGINAL);
			softly.assertAll();

			driver.switchTo().defaultContent();

			driver.findElement(By.xpath("//label[text() = 'HTML Report']")).click();
			driver.findElement(By.xpath("//label[text() = 'PDF Report']")).click();
			driver.findElement(By.xpath("//label[text() = 'XML Report']")).click();
			driver.findElement(By.xpath("//button[text() = 'Download']")).click();

			String zipFileName = NAMEBPLAN_TEST + "-Report.zip";
			withinSeconds(10, () -> assertThat(listFiles(tmpDir)).containsExactly(zipFileName));

			Path zipFile = tmpDir.resolve(zipFileName);
			try (FileSystem zipfs = FileSystems.newFileSystem(zipFile, null)) {
				Path rootInZip = zipfs.getRootDirectories().iterator().next();

				assertThat(listFiles(rootInZip)).describedAs("Content of " + zipFile)
					.containsExactlyInAnyOrder(NAMEBPLAN_TEST + ".html", NAMEBPLAN_TEST + ".pdf",
							NAMEBPLAN_TEST + ".xml");
			}
		}
		finally {
			// System.out.println("url: " + driver.getCurrentUrl());
			driver.close();
		}
	}

	private void withinSeconds(int nbSeconds, Callable<?> callable) throws Exception {
		Instant timeout = Instant.now().plusSeconds(nbSeconds);

		while (Instant.now().isBefore(timeout)) {
			try {
				callable.call();
				return;
			}
			catch (Exception | org.opentest4j.AssertionFailedError e) {
				Thread.sleep(1000);
			}
		}
		callable.call();
	}

	private List<String> listFiles(Path tmpDir) throws IOException {
		return Files.list(tmpDir).map((it) -> it.getFileName().toString()).collect(Collectors.toList());
	}

	private void verifyByXPath(WebDriver driver, String xpath, String expectedText) throws Exception {
		verifyByXPath(driver, xpath, expectedText, 5);
	}

	private void verifyByXPath(WebDriver driver, String xpath, String expectedText, int maxWaitInSeconds)
			throws Exception {
		Callable<?> c = () -> {
			WebElement elt = driver.findElement(By.xpath(xpath));
			assertEquals(expectedText, elt.getText());
			return null;
		};
		withinSeconds(maxWaitInSeconds, c);
	}

	private static String createConnectUrl(String url, String username, String password) {
		if (username != null && !username.isEmpty()) {
			String authentication = username + ":" + password + "@";
			return url.replace("://", "://" + authentication);
		}
		return url;
	}

}
