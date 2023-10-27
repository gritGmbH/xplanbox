/*-
 * #%L
 * xplan-update-data-cli - update of database
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.update.artefacts;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.core.manager.db.model.Plan;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.apache.commons.io.IOUtils;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import static de.latlon.xplan.core.manager.db.model.ArtefactType.RASTERBASIS;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.XPLANGML;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ArtefactsTableUpdater {

	private final Logger LOG = LoggerFactory.getLogger(ArtefactsTableUpdater.class);

	private PlanRepository planRepository;

	private final ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();

	public ArtefactsTableUpdater(PlanRepository planRepository) {
		this.planRepository = planRepository;
	}

	@Transactional(rollbackOn = Exception.class)
	public void update(XPlan xPlan) throws Exception {
		LOG.info("Update plan with id {}, version {}, type {}", xPlan.getId(), xPlan.getVersion(), xPlan.getType());
		int planId = Integer.parseInt(xPlan.getId());
		Optional<Plan> planCandidate = planRepository.findById(planId);
		if (!planCandidate.isPresent()) {
			LOG.warn("Plan with id {} not found.", planId);
			return;
		}
		Plan plan = planCandidate.get();
		Set<Artefact> artefacts = plan.getArtefacts();
		FeatureCollection featureCollection = retrieveFeatureCollection(xPlan, artefacts);
		if (featureCollection == null || featureCollection.isEmpty()) {
			LOG.warn("FeatureCollection is not available! Plan with id {} is skipped.", planId);
			return;
		}
		List<String> rasterFileNames = scanRasterReferenceFileNames(featureCollection);
		if (rasterFileNames.isEmpty()) {
			LOG.info("Plan with id {} has no rasterfile.", planId);
		}
		for (Artefact artefact : artefacts) {
			LOG.info("Update artefact {}", artefact.getId().getFilename());
			long length = detectLength(artefact.getData());
			ArtefactType artefactType = detectArtefactType(artefact, rasterFileNames);
			artefact.artefacttype(artefactType).length(length);
		}
		planRepository.save(plan);
	}

	private ArtefactType detectArtefactType(Artefact artefact, List<String> rasterFileNames) {
		String filename = artefact.getId().getFilename();
		if ("xplan.gml".equals(filename))
			return XPLANGML;
		if (rasterFileNames.contains(filename))
			return RASTERBASIS;
		return null;
	}

	private long detectLength(byte[] zippedData) throws IOException {
		byte[] bytes = unzipArtefact(zippedData);
		return bytes.length;
	}

	private byte[] unzipArtefact(byte[] zippedData) throws IOException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(zippedData);
				GZIPInputStream is = new GZIPInputStream(bis);
				ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			IOUtils.copy(is, bos);
			return bos.toByteArray();
		}
	}

	private FeatureCollection retrieveFeatureCollection(XPlan plan, Set<Artefact> artefacts) throws Exception {
		Optional<Artefact> xplanGmlArtefact = artefacts.stream()
			.filter(artefact -> "xplan.gml".equals(artefact.getId().getFilename()))
			.findFirst();
		if (!xplanGmlArtefact.isPresent())
			return null;
		XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
		InputStream originalPlan = new ByteArrayInputStream(unzipArtefact(xplanGmlArtefact.get().getData()));
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(originalPlan, version);
	}

	private List<String> scanRasterReferenceFileNames(FeatureCollection featureCollection) {
		ExternalReferenceInfo scan = externalReferenceScanner.scan(featureCollection);
		List<ExternalReference> rasterPlanBaseAndUpdateScans = scan.getRasterPlanBaseAndUpdateScans();
		return rasterPlanBaseAndUpdateScans.stream()
			.map(externalReference -> externalReference.getReferenzUrl())
			.collect(Collectors.toList());
	}

}
