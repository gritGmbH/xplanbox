package de.latlon.xplanbox.cli;

import de.latlon.xplanbox.cli.main.XPlanCliApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Command line tool of the xPlanBox.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
@SpringBootApplication
public class XPlanCli {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(XPlanCliApplicationRunner.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args).close();
	}

}
