/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceInfoBuilder;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.edit.ArtefactType.NONRASTER;
import static de.latlon.xplan.manager.edit.ArtefactType.RASTER;
import static de.latlon.xplan.manager.edit.ArtefactType.RASTER_GEOREFERENCE;
import static de.latlon.xplan.manager.edit.EditType.ADDED;
import static de.latlon.xplan.manager.edit.EditType.REMOVED;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.collectEditedArtefacts;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
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
	public void testCreateExternalRefAddedOrUpdated_ExternalReferenceInfo() {
		ExternalReferenceInfo externalRefsOriginalPlan = createExternalReferenceInfo("A.tif", "X.pdf", "C.png",
				"D.png");
		List<File> uploadedArtefacts = createUploadedFileList("A.tif", "B.jpg", "C.png");

		ExternalReferenceInfo externalReferenceInfo = createExternalRefAddedOrUpdated(externalRefsOriginalPlan,
				uploadedArtefacts);

		List<ExternalReference> rasterPlanBaseAndUpdateScans = externalReferenceInfo.getRasterPlanBaseAndUpdateScans();
		assertThat(rasterPlanBaseAndUpdateScans.size(), is(2));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("A.tif"));
		assertThat(rasterPlanBaseAndUpdateScans, hasExternalReference("C.png"));
	}

	@Test
	public void testCreateExternalRefAddedOrUpdated_XPlanToEdit() {
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
	public void testCollectEditedArtefacts() {
		List<String> originalFileNames = Arrays.asList("A.tif", "D.png", "D.pgw", "F.png", "F.pgw");
		List<String> uploadedFileNames = Arrays.asList("A.tif", "X.pdf", "Z.pdf", "B.png", "C.png", "C.pgw");
		ExternalReferenceInfo externalRefsModified = new ExternalReferenceInfoBuilder()
			.addRasterPlanBaseScan(new ExternalReference("A.tif"))
			.addNonRasterReference(new ExternalReference("X.pdf"))
			.addNonRasterReference(new ExternalReference("Z.pdf"))
			.addRasterPlanUpdateScan(new ExternalReference("C.png", "C.pgw"))
			.addRasterPlanUpdateScan(new ExternalReference("D.png", "D.pgw"))
			.build();
		ExternalReferenceInfo externalRefsOriginal = new ExternalReferenceInfoBuilder()
			.addRasterPlanBaseScan(new ExternalReference("A.tif"))
			.addNonRasterReference(new ExternalReference("X.pdf"))
			.addNonRasterReference(new ExternalReference("Y.pdf"))
			.addRasterPlanUpdateScan(new ExternalReference("D.png", "D.pgw"))
			.addRasterPlanUpdateScan(new ExternalReference("F.png", "F.pgw"))
			.build();

		EditedArtefacts editedArtefacts = collectEditedArtefacts(null, "1", externalRefsModified, externalRefsOriginal,
				originalFileNames, uploadedFileNames);

		List<String> removedRaster = editedArtefacts.getFileNames(REMOVED, RASTER, RASTER_GEOREFERENCE);
		assertThat(removedRaster.size(), is(2));
		assertThat(removedRaster, hasItems("F.png", "F.pgw"));

		List<String> addedRaster = editedArtefacts.getFileNames(ADDED, RASTER, RASTER_GEOREFERENCE);
		assertThat(addedRaster.size(), is(2));
		assertThat(addedRaster, hasItems("C.png", "C.pgw"));

		List<String> removedNonRaster = editedArtefacts.getFileNames(REMOVED, NONRASTER);
		assertThat(removedNonRaster.size(), is(1));
		assertThat(removedNonRaster, hasItems("Y.pdf"));

		List<String> addedNonRaster = editedArtefacts.getFileNames(ADDED, NONRASTER);
		assertThat(addedNonRaster.size(), is(1));
		assertThat(addedNonRaster, hasItems("Z.pdf"));
	}

	private ExternalReferenceInfo createExternalReferenceInfo(String rasterPlanBaseScan, String nonRasterReference,
			String... rasterUploads) {
		List<ExternalReference> updateScans = Arrays.stream(rasterUploads)
			.map(rasterUpload -> new ExternalReference(rasterUpload))
			.collect(Collectors.toList());
		ExternalReference nonRasterRef = nonRasterReference != null ? new ExternalReference(nonRasterReference) : null;
		return new ExternalReferenceInfoBuilder().addRasterPlanBaseScan(new ExternalReference(rasterPlanBaseScan))
			.addRasterPlanUpdateScans(updateScans)
			.addNonRasterReference(nonRasterRef)
			.build();
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
