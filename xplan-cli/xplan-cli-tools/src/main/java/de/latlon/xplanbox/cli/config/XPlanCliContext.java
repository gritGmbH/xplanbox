/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
