/*-
 * #%L
 * xplan-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.job.validator.memory;

import de.latlon.xplan.commons.util.XmlUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.job.validator.memory.InsertedFids.INSERTED_FIDS_KEY;
import static org.deegree.gml.GMLVersion.GML_32;
import static org.deegree.protocol.wfs.transaction.action.IDGenMode.GENERATE_NEW;

/**
 * Job to import GML files.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GmlImportJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(GmlImportJob.class);

	private static final String MEMORY_FEATURESTORE = "xplansyn";

	@Autowired
	private DeegreeWorkspace workspace;

	@Override
	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
		File workspaceLocation = workspace.getLocation();
		Path path = Paths.get(workspaceLocation.toURI()).resolve("data");
		if (!Files.exists(path)) {
			return;
		}
		try {
			SchedulerContext context = jobExecutionContext.getScheduler().getContext();
			List<InsertedFids> insertedFids = getInsertedFids(context);
			Files.find(path, Integer.MAX_VALUE, (p, bfa) -> Files.isRegularFile(p)).forEach(p -> {
				List<String> fids = importGml(p, workspace);
				insertedFids.add(new InsertedFids(fids));
				context.put(INSERTED_FIDS_KEY, insertedFids);
			});
		}
		catch (IOException e) {
			LOG.warn("Could not find GML files to insert", e);
		}
		catch (SchedulerException e) {
			LOG.warn("Could not retrieve scheduler context", e);
		}
		LOG.trace("GmlImportJob done");
	}

	private List<InsertedFids> getInsertedFids(SchedulerContext jobDataMap) {
		if (!jobDataMap.containsKey(INSERTED_FIDS_KEY))
			return Collections.synchronizedList(new ArrayList<>());
		@SuppressWarnings("unchecked")
		List<InsertedFids> insertedFids = (List<InsertedFids>) jobDataMap.get(INSERTED_FIDS_KEY);
		jobDataMap.put(INSERTED_FIDS_KEY, insertedFids);
		return insertedFids;
	}

	@SuppressFBWarnings(value = "XXE_XMLSTREAMREADER")
	private List<String> importGml(Path p, DeegreeWorkspace workspace) {
		LOG.info("Insert {}", p);
		XMLStreamReader xmlStreamReader = null;
		GMLStreamReader gmlStreamReader = null;
		FeatureStoreTransaction ta = null;
		try (InputStream inputStream = Files.newInputStream(p)) {
			xmlStreamReader = XmlUtils.createXMLInputFactory().createXMLStreamReader(inputStream);
			gmlStreamReader = GMLInputFactory.createGMLStreamReader(GML_32, xmlStreamReader);
			FeatureCollection fc = gmlStreamReader.readFeatureCollection();
			FeatureStore fs = workspace.getNewWorkspace().getResource(FeatureStoreProvider.class, MEMORY_FEATURESTORE);
			ta = fs.acquireTransaction();
			List<String> fids = ta.performInsert(fc, GENERATE_NEW);
			LOG.info("Inserted featureCollection with " + fids.size() + " features in memory.");
			ta.commit();
			LOG.info("Remove inserted gml file {} from data directory", p);
			Files.delete(p);
			return fids;
		}
		catch (Exception e) {
			LOG.warn("Could not add featureCollection", e);
			rollbackQuietly(ta);
		}
		finally {
			closeQuietly(gmlStreamReader);
			closeQuietly(xmlStreamReader);
		}
		return Collections.emptyList();
	}

	private void closeQuietly(GMLStreamReader gmlStreamReader) {
		if (gmlStreamReader != null) {
			try {
				gmlStreamReader.close();
			}
			catch (XMLStreamException e) {
			}
		}
	}

	private void closeQuietly(XMLStreamReader xmlStreamReader) {
		if (xmlStreamReader != null) {
			try {
				xmlStreamReader.close();
			}
			catch (XMLStreamException e) {
			}
		}
	}

	private void rollbackQuietly(FeatureStoreTransaction ta) {
		if (ta != null) {
			try {
				ta.rollback();
			}
			catch (FeatureStoreException ex) {
			}
		}
	}

}
