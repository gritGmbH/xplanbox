/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.collectRemovedRefs;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefRemovedOrUpdated;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ExternalReferenceUtilsTest {

	@Test
	public void testCreateExternalRefAddedOrUpdated_ExternalReferenceInfo() throws Exception {
		ExternalReferenceInfo externalRefsOriginalPlan = createExternalReferenceInfo("A.tif", "C.png", "D.png");
		List<File> uploadedArtefacts = createUploadedFileList("A.tif", "B.jpg", "C.png");

		ExternalReferenceInfo externalReferenceInfo = createExternalRefAddedOrUpdated(externalRefsOriginalPlan,
				uploadedArtefacts);

		List<ExternalReference> rasterPlanBaseAndUpdateScans = externalReferenceInfo.getRasterPlanBaseAndUpdateScans();
		assertThat(rasterPlanBaseAndUpdateScans.size(), is(2));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("A.tif"));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("C.png"));
	}

	@Test
	public void testCreateExternalRefAddedOrUpdated_XPlanToEdit() throws Exception {
		XPlanToEdit planToEdit = new XPlanToEdit();
		RasterBasis rasterBasis = new RasterBasis();
		RasterReference rasterReference1 = new RasterReference("0", "G.tif", null, LEGEND, null, null, null, null, null,
				null, null);
		RasterReference rasterReference2 = new RasterReference("0", "A.tif", null, SCAN, null, null, null, null, null,
				null, null);
		rasterBasis.addRasterReference(rasterReference1);
		rasterBasis.addRasterReference(rasterReference2);
		planToEdit.addRasterBasis(rasterBasis);

		List<File> uploadedArtefacts = createUploadedFileList("A.tif", "B.jpg", "C.png");

		ExternalReferenceInfo externalReferenceInfo = createExternalRefAddedOrUpdated(planToEdit, uploadedArtefacts);

		List<ExternalReference> rasterPlanBaseAndUpdateScans = externalReferenceInfo.getRasterPlanBaseAndUpdateScans();
		assertThat(rasterPlanBaseAndUpdateScans.size(), is(1));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("A.tif"));
	}

	@Test
	public void testCreateExternalRefRemovedOrUpdated() throws Exception {
		ExternalReferenceInfo externalRefsModified = createExternalReferenceInfo("A.tif", "C.png", "D.png");
		List<File> uploadedArtefacts = createUploadedFileList("A.tif", "B.jpg", "C.png");
		ExternalReferenceInfo externalRefsOriginal = createExternalReferenceInfo("A.tif", "D.png", "F.png");

		ExternalReferenceInfo externalReferences = createExternalRefRemovedOrUpdated(externalRefsModified,
				uploadedArtefacts, externalRefsOriginal);

		List<ExternalReference> rasterPlanBaseAndUpdateScans = externalReferences.getRasterPlanBaseAndUpdateScans();
		assertThat(rasterPlanBaseAndUpdateScans.size(), is(2));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("A.tif"));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("F.png"));
	}

	@Test
	public void testCollectRemovedRefs() throws Exception {
		ExternalReferenceInfo externalRefsModified = createExternalReferenceInfo("A.tif", "C.png", "D.png");
		ExternalReferenceInfo externalRefsOriginal = createExternalReferenceInfo("A.tif", "D.png", "F.png");

		Set<String> removedRefs = collectRemovedRefs(externalRefsModified, externalRefsOriginal);

		assertThat(removedRefs.size(), is(1));
		assertThat(removedRefs, hasItems("F.png"));
	}

	private ExternalReferenceInfo createExternalReferenceInfo(String rasterPlanBaseScan, String... rasterUploads) {
		ExternalReferenceInfo externalReferenceInfo = new ExternalReferenceInfo();
		externalReferenceInfo.addRasterPlanBaseScan(new ExternalReference(rasterPlanBaseScan));
		for (String rasterUpload : rasterUploads) {
			externalReferenceInfo.addRasterPlanUpdateScan(new ExternalReference(rasterUpload));
		}
		externalReferenceInfo.addExternalRefs(externalReferenceInfo.getRasterPlanBaseAndUpdateScans());
		return externalReferenceInfo;
	}

	private List<File> createUploadedFileList(String... fileNames) {
		List<File> uploadedArtifacts = new ArrayList<>();
		for (String fileName : fileNames) {
			uploadedArtifacts.add(new File(fileName));
		}
		return uploadedArtifacts;
	}

	private Matcher<List<ExternalReference>> hasExternalReference(final String referenceUrl) {
		return new TypeSafeMatcher<List<ExternalReference>>() {
			@Override
			protected boolean matchesSafely(List<ExternalReference> externalReferences) {
				for (ExternalReference externalReference : externalReferences) {
					if (referenceUrl.equals(externalReference.getReferenzUrl()))
						return true;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(
						"ExternalReference list shall contain at least one ExternalReference with referenceURL ");
				description.appendValue(referenceUrl);
			}

		};
	}

}
