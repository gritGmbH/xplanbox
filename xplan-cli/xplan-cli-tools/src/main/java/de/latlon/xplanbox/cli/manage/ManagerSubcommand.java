package de.latlon.xplanbox.cli.manage;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplanbox.cli.XPlanCliSubcommand;
import de.latlon.xplanbox.cli.manage.config.ManageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class ManagerSubcommand extends XPlanCliSubcommand {

	public Integer callSubcommand(ApplicationContext applicationContext) {
		XPlanManager xPlanManager = applicationContext.getBean(XPlanManager.class);
		return callSubcommand(xPlanManager);
	}

	public void register(AnnotationConfigApplicationContext applicationContext) {
		applicationContext.register(ManageContext.class);
	}

	abstract Integer callSubcommand(XPlanManager xPlanManager);

}
