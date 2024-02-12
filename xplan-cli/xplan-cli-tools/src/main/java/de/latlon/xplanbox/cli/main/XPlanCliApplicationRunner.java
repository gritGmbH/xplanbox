package de.latlon.xplanbox.cli.main;

import de.latlon.xplanbox.cli.config.XPlanCliContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Import(XPlanCliContext.class)
public class XPlanCliApplicationRunner implements CommandLineRunner, ExitCodeGenerator {

	@Autowired
	private MainCommand mainCommand;

	@Autowired
	private ApplicationContext applicationContext;

	private int exitCode;

	@Override
	public void run(String... args) throws Exception {
		exitCode = new CommandLine(mainCommand, new picocli.spring.PicocliSpringFactory(applicationContext))
			.execute(args);
	}

	@Override
	public int getExitCode() {
		return exitCode;
	}

}
