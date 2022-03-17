/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.web.shared.edit;

import java.util.ArrayList;
import java.util.List;

/**
 * Summarizes all properties of a plan to edit.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanToEdit {

	private BaseData baseData;

	private ValidityPeriod validityPeriod;

	private List<Change> changes;

	private List<Text> texts;

	private List<Reference> references;

	private RasterBasis rasterBasis;

	private boolean hasBereich = false;

	public XPlanToEdit() {
	}

	/**
	 * @param baseData may be <code>null</code>
	 * @param validityPeriod may be <code>null</code>
	 * @param changes may be <code>null</code>
	 * @param texts may be <code>null</code>
	 * @param references may be <code>null</code>
	 * @param rasterBasis may be <code>null</code>
	 * @param hasBereich <code>true</code> if the plan has a BP_Bereich,
	 * <code>false</code> otherwise
	 */
	public XPlanToEdit(BaseData baseData, ValidityPeriod validityPeriod, List<Change> changes, List<Text> texts,
			List<Reference> references, RasterBasis rasterBasis, boolean hasBereich) {
		this.baseData = baseData;
		this.validityPeriod = validityPeriod;
		this.changes = changes;
		this.texts = texts;
		this.references = references;
		this.rasterBasis = rasterBasis;
		this.hasBereich = hasBereich;
	}

	/**
	 * @return base data of the plan, never <code>null</code>
	 */
	public BaseData getBaseData() {
		if (this.baseData == null)
			this.baseData = new BaseData();
		return baseData;
	}

	/**
	 * @param baseData baseData to set, may be <code>null</code>
	 */
	public void setBaseData(BaseData baseData) {
		this.baseData = baseData;
	}

	/**
	 * @return the validityPeriod, never <code>null</code>
	 */
	public ValidityPeriod getValidityPeriod() {
		if (this.validityPeriod == null)
			this.validityPeriod = new ValidityPeriod();
		return validityPeriod;
	}

	/**
	 * @param validityPeriod the validityPeriod to set, may be <code>null</code>
	 */
	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	/**
	 * @return the changes, never <code>null</code>
	 */
	public List<Change> getChanges() {
		if (changes == null)
			changes = new ArrayList<Change>();
		return changes;
	}

	/**
	 * @param changes the changes to set, may be <code>null</code>
	 */
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	/**
	 * @param change to add, may be <code>null</code> (nothing is added)
	 */
	public void addChange(Change change) {
		if (change != null)
			getChanges().add(change);
	}

	/**
	 * @return the text, never <code>null</code>
	 */
	public List<Text> getTexts() {
		if (texts == null)
			texts = new ArrayList<Text>();
		return texts;
	}

	/**
	 * @param texts the text to set, may be <code>null</code>
	 */
	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}

	/**
	 * @param text to add, may be <code>null</code> (nothing is added)
	 */
	public void addText(Text text) {
		if (text != null)
			getTexts().add(text);
	}

	/**
	 * @return the references, never <code>null</code>
	 */
	public List<Reference> getReferences() {
		if (references == null)
			references = new ArrayList<Reference>();
		return references;
	}

	/**
	 * @param references the references to set, may be <code>null</code>
	 */
	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	/**
	 * @param reference to add, may be <code>null</code> (nothing is added)
	 */
	public void addReference(Reference reference) {
		if (reference != null)
			getReferences().add(reference);
	}

	/**
	 * @return the rasterBasis, may be <code>null</code>
	 */
	public RasterBasis getRasterBasis() {
		return rasterBasis;
	}

	/**
	 * @param rasterBasis the rasterBasis to set, may be <code>null</code>
	 */
	public void setRasterBasis(RasterBasis rasterBasis) {
		this.rasterBasis = rasterBasis;
	}

	/**
	 * @return <code>true</code> if the plan has a BP_Bereich, <code>false</code>
	 * otherwise
	 */
	public boolean isHasBereich() {
		return hasBereich;
	}

	/**
	 * @param hasBereich <code>true</code> if the plan has a BP_Bereich,
	 * <code>false</code> otherwise
	 */
	public void setHasBereich(boolean hasBereich) {
		this.hasBereich = hasBereich;
	}

	@Override
	public String toString() {
		return "XPlanToEdit {baseData=" + baseData + ", validityPeriod=" + validityPeriod + ", changes=" + changes
				+ ", texts=" + texts + ", references=" + references + ", rasterBasis=" + rasterBasis + ", hasBereich="
				+ hasBereich + "}";
	}

}
