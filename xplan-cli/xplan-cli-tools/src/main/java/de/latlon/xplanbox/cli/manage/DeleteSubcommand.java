package de.latlon.xplanbox.cli.manage;

import de.latlon.xplan.manager.XPlanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "delete", description = "Delete a single or multiple plan(s).",
		subcommands = { CommandLine.HelpCommand.class })
public class DeleteSubcommand extends ManagerSubcommand {

	private static final Logger LOG = LoggerFactory.getLogger(DeleteSubcommand.class);

	@CommandLine.Option(names = { "-i", "--id" }, split = ",",
			description = "Die ID des Plans der geloescht werden soll.", required = true)
	private String[] ids;

	@Override
	public Integer callSubcommand(XPlanManager xPlanManager) {
		List<String> planIds = Arrays.asList(ids);
		if (planIds.isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("Keine planIds angegeben.");
			return 0;
		}
		for (String planId : planIds) {
			System.out.println("---------------------------------------");
			try {
				xPlanManager.delete(planId);
				System.out.println("XPlan " + planId + " wurde geloescht.");
			}
			catch (Exception e) {
				LOG.debug("Loeschen des Plans mit der id " + planId + " fehlgeschlagen.", e);
				LOG.error("Loeschen des Plans mit der id " + planId + " fehlgeschlagen. Fehlermeldung: "
						+ e.getLocalizedMessage());
				return 1;
			}
		}
		return 0;
	}

}
