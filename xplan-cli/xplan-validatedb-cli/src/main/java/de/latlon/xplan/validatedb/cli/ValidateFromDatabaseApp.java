package de.latlon.xplan.validatedb.cli;

import de.latlon.xplan.validatedb.cli.config.ValidateFromDatabaseConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the command line interface of the GMLLoader.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@SpringBootApplication
public class ValidateFromDatabaseApp {

	public static void main(String[] args) {
		if (args.length == 1
				|| (args.length > 1 && ("--help".equals(args[1]) || "-help".equals(args[1]) || "-h".equals(args[1])))) {
			printUsage();
		}
		else if (args.length != 4) {
			printUsage();
		}
		else {
			SpringApplication app = new SpringApplication(ValidateFromDatabaseConfiguration.class);
			app.setBannerMode(Banner.Mode.OFF);
			app.run(args);
		}
	}

	private static void printUsage() {
		System.out.println(
				"Usage: ./XPlanValidateDB -jdbcurl=jdbc:postgresql://localhost:5432/xplanbox -user=postgres -password=postgres -rulesDirectory=../etc/rules");
		System.out.println();
		System.out.println("Options:");
		System.out.println(" -jdbcurl=jdbc:postgresql://localhost:5432/xplanbox");
		System.out.println(" -user=postgres");
		System.out.println(" -password=postgres");
		System.out.println(" -rulesDirectory=tmp/rules");
	}

}