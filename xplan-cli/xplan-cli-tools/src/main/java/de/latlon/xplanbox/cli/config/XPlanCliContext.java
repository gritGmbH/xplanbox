package de.latlon.xplanbox.cli.config;

import de.latlon.xplanbox.cli.admin.config.DistrictUpdateContext;
import de.latlon.xplanbox.cli.admin.config.CommonContext;
import de.latlon.xplanbox.cli.admin.config.ReSynthesizerContext;
import de.latlon.xplanbox.cli.admin.config.SortdateUpdateContext;
import de.latlon.xplanbox.cli.admin.db.SortPropertyDbUpdater;
import de.latlon.xplanbox.cli.manage.config.ManageContext;
import de.latlon.xplanbox.cli.validate.config.ValidateFileContext;
import de.latlon.xplanbox.cli.validate.config.ValidateFromDatabaseContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
@ComponentScan(
		value = { "de.latlon.xplanbox.cli.main", "de.latlon.xplanbox.cli.validate", "de.latlon.xplanbox.cli.manage",
				"de.latlon.xplanbox.cli.admin" },
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
				value = { ValidateFileContext.class, ValidateFromDatabaseContext.class, ManageContext.class,
						DistrictUpdateContext.class, CommonContext.class, ReSynthesizerContext.class,
						SortdateUpdateContext.class, SortPropertyDbUpdater.class }))
public class XPlanCliContext {

}
