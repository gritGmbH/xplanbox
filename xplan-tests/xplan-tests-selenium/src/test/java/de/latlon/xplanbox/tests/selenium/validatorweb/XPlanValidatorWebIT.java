/*-
 * #%L
 * xplan-tests-selenium - Modul zur Gruppierung aller Module zum testen
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplanbox.tests.selenium.validatorweb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XPlanValidatorWebIT {

	private static final String BPLAN_VALID = "BPlan001_5-4";

	private static final String BPLAN_INVALID = "xplan52-Laufrichtungsfehler";

	private static final String VALIDATION_NAME = "Selenium_Test";

	private static final String DOWNLOADED_FILE_NAME = "Selenium_Test-Report.zip";

	private static String connectUrl;

	@BeforeAll
	static void readSystemProperties() {
		String url = System.getProperty("baseUrlValidatorWeb", "https://xplanbox.lat-lon.de/xplan-validator-web");
		String username = System.getProperty("username", null);
		String password = System.getProperty("password", null);
		connectUrl = createConnectUrl(url, username, password);
	}

	/**
	 * Diese Methode testet den XPlanValidatorWeb mit einem gültigen Plan.
	 *
	 * <p>
	 * Dieser Test deckt mit einem gültigen Planwerk die folgenden Testfälle ab: <br>
	 * - Teilschritt Webschnittstelle XPlanValidator: 1.1<br>
	 * - Teilschritt Planarchiv auswählen: 2.1, 2.2, 2.3<br>
	 * - Teilschritt Eingabe einer Bezeichnung für den Validierungsdurchlauf: 3.1, 3.2,
	 * 3.3, 3.4, 3.5, 3.6<br>
	 * - Teilschritt Auswahl eines Validierungstyps: 4.1, 4.2<br>
	 * - Teilschritt Auswahl eines Profils wird nicht getestet (ist aktuell
	 * auskommentiert)<br>
	 * - Teilschritt Validierung starten und abbrechen: 6.2<br>
	 * - Teilschritt Download der Validierungsergebnisse: Hier wird bisher nur PDF und
	 * Shape heruntergeladen<br>
	 *
	 * Zudem wird nach einmaliger Validierung zu den Validierungsoptionen zurückgesprungen
	 * und erneut validiert. Anschließend wird in den Startbildschirm zurückgegangen, wo
	 * ein neue Plan validiert werden könnte
	 * </p>
	 */
	@Test
	void test_XPlanValidatorWeb_withValidPlan(@TempDir(cleanup = CleanupMode.ON_SUCCESS) final Path tmpDir) {
		WebDriver driver = createWebDriver(tmpDir);
		driver.get(connectUrl);
		String fileToValidate = getClass().getResource("/" + BPLAN_VALID + ".zip").getFile().toString();
		assertDoesNotThrow(() -> {
			preValidate(driver, fileToValidate, VALIDATION_NAME);
			startValidation(driver);
			sleep();
			checkIfValidAfterValidation(driver, VALIDATION_NAME, BPLAN_VALID);
			downloadPDFandShape(driver);
			sleep();
			Path downloadedFile = tmpDir.resolve(DOWNLOADED_FILE_NAME);
			checkDownloadedFileSize(downloadedFile);
			recheckValidationOptionsAndQuit2newPlan(driver);
			sleep();
		});
		driver.close();
	}

	/**
	 * Diese Methode testet den XPlanValidatorWeb mit einem invalieden Plan.
	 *
	 * <p>
	 * Dieser Test deckt mit einem geometrisch invaliden Planwerk die folgenden Testfälle
	 * ab: <br>
	 * - Teilschritt Webschnittstelle XPlanValidator: 1.1<br>
	 * - Teilschritt Planarchiv auswählen: 2.1, 2.2, 2.3<br>
	 * - Teilschritt Eingabe einer Bezeichnung für den Validierungsdurchlauf: 3.1, 3.2,
	 * 3.3, 3.4, 3.5, 3.6<br>
	 * - Teilschritt Auswahl eines Validierungstyps: 4.1, 4.2<br>
	 * - Teilschritt Auswahl eines Profils wird nicht getestet (ist aktuell
	 * auskommentiert)<br>
	 * - Teilschritt Validierung starten und abbrechen: 6.2<br>
	 * - Teilschritt Download der Validierungsergebnisse: Hier wird bisher nur PDF und
	 * Shape heruntergeladen<br>
	 *
	 * Zudem wird nach einmaliger Validierung zu den Validierungsoptionen zurückgesprungen
	 * und erneut validiert. Anschließend wird in den Startbildschirm zurückgegangen, wo
	 * ein neue Plan validiert werden könnte
	 * </p>
	 */
	@Test
	void test_XPlanValidatorWeb_withInValidPlan(@TempDir(cleanup = CleanupMode.ON_SUCCESS) final Path tmpDir) {
		WebDriver driver = createWebDriver(tmpDir);
		driver.get(connectUrl);
		String fileToValidate = getClass().getResource("/" + BPLAN_INVALID + ".zip").getFile().toString();
		assertDoesNotThrow(() -> {
			preValidate(driver, fileToValidate, VALIDATION_NAME);
			startValidation(driver);
			sleep();
			checkIfInvalidAfterValidation(driver, VALIDATION_NAME);
			downloadPDFandShape(driver);
			sleep();
			Path downloadedFile = tmpDir.resolve(DOWNLOADED_FILE_NAME);
			checkDownloadedFileSize(downloadedFile);
			recheckValidationOptionsAndQuit2newPlan(driver);
			sleep();
		});
		driver.close();
	}

	private static WebDriver createWebDriver(Path tmpDir) {
		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("download.default_directory", tmpDir.toString());
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--no-sandbox", "--ignore-certificate-errors", "--headless", "--disable-dev-shm-usage",
				"--disable-gpu", "--disable-software-rasterizer");
		return new ChromeDriver(options);
	}

	private static String createConnectUrl(String url, String username, String password) {
		if (username != null && !username.isEmpty()) {
			String authentication = username + ":" + password + "@";
			return url.replace("://", "://" + authentication);
		}
		return url;
	}

	private static void preValidate(WebDriver driver, String fileToValidate, final String validationName)
			throws InterruptedException {
		sleep();
		WebElement elemAdd = driver.findElement(By.name("uploadPlanItem"));
		elemAdd.sendKeys(fileToValidate);
		driver.findElement(By.xpath("//button[text()='Hochladen und Validierungsoptionen einstellen']")).click();

		sleep();
		boolean isLoading = true;
		while (isLoading) {
			try {
				// einmal abbrechen und neu validieren
				driver.findElement(By.xpath("//button[text()='abbrechen']")).click();
				sleep();
				isLoading = false;
			}
			catch (Exception e) {
			}
		}
		driver.findElement(By.xpath("//button[text()='Hochladen und Validierungsoptionen einstellen']")).click();

		sleep();
		isLoading = true;
		while (isLoading) {
			try {
				// validieren
				driver.findElement(By.xpath("//button[text()='zur Validierung']")).click();
				sleep();
				isLoading = false;
			}
			catch (Exception e) {
			}
		}
		WebElement elemName = driver.findElement(By.xpath("//input[@class = 'gwt-TextBox' and @type = 'text']"));
		elemName.clear();

		sleep();
		elemName.sendKeys(validationName);
		driver.findElement(By.xpath("//tr[5]/td/span/input")).click();

		sleep();
		driver.findElement(By.xpath("//tr[5]/td/span/input")).click();
		sleep();
	}

	private static void startValidation(WebDriver driver) {
		driver.findElement(By.xpath("//button[text()='Validierung starten']")).click();
		boolean isValidating = true;
		while (isValidating) {
			try {
				// gucken ab wann er mit dem Validieren durch ist
				sleep();
				driver.findElement(By.xpath("//Button[text()='Weiteren Plan validieren']"));
				isValidating = false;
			}
			catch (Exception e) {
			}
		}
	}

	private static void checkIfValidAfterValidation(WebDriver driver, final String NAMEBPLAN_TEST,
			final String NAMEBPLAN_ORIGINAL) {
		WebElement iframe = driver
			.findElement(By.xpath("//html/body/div[4]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td[1]/iframe"));
		driver.switchTo().frame(iframe);

		String name = driver.findElement(By.xpath("//body/p[1]/b")).getText();
		String valide = driver.findElement(By.xpath("//body/p[4]/b/font")).getText();
		String planname = driver.findElement(By.xpath("//body/ul[1]/b/li[1]")).getText();

		assertEquals("valide", valide);
		assertEquals(NAMEBPLAN_TEST, name);
		assertEquals(NAMEBPLAN_ORIGINAL, planname);
	}

	private static void checkIfInvalidAfterValidation(WebDriver driver, final String NAMEBPLAN_TEST) {
		WebElement iframe = driver
			.findElement(By.xpath("//html/body/div[4]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td[1]/iframe"));
		driver.switchTo().frame(iframe);

		String name = driver.findElement(By.xpath("//body/p[1]/b")).getText();
		String valide = driver.findElement(By.xpath("//body/p[4]/b/font")).getText();
		String planname = driver.findElement(By.xpath("//body/ul[1]/b/li[1]")).getText();

		assertEquals("nicht valide", valide);
		assertEquals(NAMEBPLAN_TEST, name);
		assertEquals(BPLAN_INVALID, planname);

	}

	private static void downloadPDFandShape(WebDriver driver) throws InterruptedException {
		driver.switchTo().defaultContent();
		sleep();
		driver.findElement(By.xpath("//table/tbody/tr[2]/td/span/input")).click();
		sleep();
		driver.findElement(By.xpath("//button[text() = 'Download']")).click();
		sleep();
	}

	private static void checkDownloadedFileSize(Path downloadedFile) throws IOException {
		FileChannel imageFileChannel = FileChannel.open(downloadedFile);
		long fileSize = imageFileChannel.size();
		assertTrue(fileSize > 0);
		// TODO: check content
	}

	private static void recheckValidationOptionsAndQuit2newPlan(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Zurück zu den Validierungseinstellungen']")).click();
		sleep();
		driver.switchTo().defaultContent();
		startValidation(driver);
		sleep();
		driver.findElement(By.xpath("//button[text()='Weiteren Plan validieren']")).click();
	}

	public static void sleep() throws InterruptedException {
		Thread.sleep(1000);
	}

}
