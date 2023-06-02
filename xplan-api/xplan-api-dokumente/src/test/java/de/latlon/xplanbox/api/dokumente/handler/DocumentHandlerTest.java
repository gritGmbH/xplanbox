/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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
package de.latlon.xplanbox.api.dokumente.handler;

import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactId;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.core.manager.db.model.Bereich;
import de.latlon.xplan.core.manager.db.model.Feature;
import de.latlon.xplan.core.manager.db.model.Plan;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplanbox.api.dokumente.config.ApplicationContext;
import de.latlon.xplanbox.api.dokumente.config.HsqlJpaContext;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @since 6.1
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DocumentHandlerTest.DocumentHandlerTestContext.class, HsqlJpaContext.class,
		ApplicationContext.class })
@Transactional
@Ignore
public class DocumentHandlerTest {

	@Autowired
	private DocumentHandler documentHandler;

	@Autowired
	private PlanRepository planRepository;

	@Test
	@Commit
	public void test_listDocuments() throws Exception {
		Plan plan = createPlanWithArtefact();
		planRepository.save(plan);

		List<Document> documents = documentHandler.listDocuments(String.valueOf(plan.getId()));
		assertTrue(documents.size() == 1);
	}

	@Test
	@Commit
	public void test_headDocument() throws Exception {
		Plan plan = createPlanWithArtefact();
		planRepository.save(plan);

		DocumentHeader documentHeader = documentHandler.headDocument(String.valueOf(plan.getId()), "test.xml");
		assertTrue(documentHeader.getFileSize() == 24);
		assertTrue(documentHeader.getMediaType().equals("text/xml"));
	}

	@Test
	@Commit
	public void test_getDocument() throws Exception {
		Plan plan = createPlanWithArtefact();
		planRepository.save(plan);

		DocumentHeaderWithStream documentHeader = documentHandler.getDocument(String.valueOf(plan.getId()), "test.xml");
		assertTrue(documentHeader.getFileSize() == 24);
		assertTrue(documentHeader.getMediaType().equals("text/xml"));
		assertTrue(documentHeader.getStreamingOutput() != null);
	}

	private Plan createPlanWithArtefact() throws IOException {
		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Plan plan = new Plan();
		byte[] bytes = "test".getBytes(UTF_8);
		ArtefactId artefactId = new ArtefactId().plan(plan).filename("test.xml");
		Artefact artefact = new Artefact().id(artefactId).num(1).artefacttype(ArtefactType.XPLANGML)
				.mimetype("text/xml").length(Long.valueOf(bytes.length)).data(createZipArtefact(bytes));
		return plan.importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false)
				.bereiche(Collections.singleton(bereich)).features(Collections.singleton(feature))
				.artefacts(Collections.singleton(artefact));
	}

	private byte[] createZipArtefact(byte[] bytes) throws IOException {
		InputStream is = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		copyLarge(is, gos);
		gos.close();
		return bos.toByteArray();
	}

	@Configuration
	static class DocumentHandlerTestContext {

		@Bean
		@Primary
		public ManagerWorkspaceWrapper managerWorkspaceWrapper(ManagerConfiguration managerConfiguration)
				throws WorkspaceException {
			return mock(ManagerWorkspaceWrapper.class);
		}

	}

}
