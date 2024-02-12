package de.latlon.xplanbox.cli;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import picocli.CommandLine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Base class for all sub commands defining the option of all sub commands.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanCliSubcommand implements Callable<Integer> {

	@CommandLine.Option(names = { "-v", "--verbose" }, defaultValue = "false",
			description = "Print the system log (default: ${DEFAULT-VALUE})")
	private boolean verbose;

	@CommandLine.Option(names = { "--config" }, description = "Path to the XPLANBOX_CONFIG directory.")
	private File config;

	@CommandLine.Option(names = { "--workspace" }, description = "Path to the DEEGREE_WORKSPACE_ROOT directory.")
	private File workspace;

	@Override
	public Integer call() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		register(applicationContext);
		ConfigurableEnvironment env = applicationContext.getEnvironment();
		Map<String, Object> map = new HashMap();
		map.put("xplanbox.config", config != null ? config.getAbsolutePath() : null);
		map.put("xplanbox.workspace", workspace != null ? workspace : null);
		PropertySource<?> propertySource = new MapPropertySource("manager", map);
		env.getPropertySources().addLast(propertySource);
		applicationContext.setEnvironment(env);
		applicationContext.refresh();
		return callSubcommand(applicationContext);
	}

	protected abstract void register(AnnotationConfigApplicationContext applicationContext);

	protected abstract Integer callSubcommand(ApplicationContext applicationContext) throws Exception;

}
