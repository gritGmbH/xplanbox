/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Summarizes all properties of a plan to edit.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanToEdit implements Serializable {

	@Valid
	private BaseData baseData;

	@Valid
	private ValidityPeriod validityPeriod;

	@Valid
	private List<Change> changes = new ArrayList<Change>();

	@Valid
	private List<Text> texts = new ArrayList<Text>();

	@Valid
	private List<Reference> references = new ArrayList<Reference>();

	@Valid
	private List<RasterBasis> rasterBasis = new ArrayList<RasterBasis>();

	private boolean hasBereich = false;

	public XPlanToEdit() {
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
	 * @deprecated will be removed in a future version.
	 * @return the validityPeriod, never <code>null</code>
	 */
	@Deprecated
	public ValidityPeriod getValidityPeriod() {
		if (this.validityPeriod == null)
			this.validityPeriod = new ValidityPeriod();
		return validityPeriod;
	}

	/**
	 * @deprecated will be removed in a future version.
	 * @param validityPeriod the validityPeriod to set, may be <code>null</code>
	 */
	@Deprecated
	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	/**
	 * @return the changes, never <code>null</code>
	 */
	public List<Change> getChanges() {
		return changes;
	}

	/**
	 * @param changes the changes to set, may be <code>null</code>
	 */
	public void setChanges(List<Change> changes) {
		if (changes != null) {
			this.changes = new ArrayList<Change>(changes);
		}
		else {
			this.changes = new ArrayList<Change>();
		}
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
		return texts;
	}

	/**
	 * @param texts the text to set, may be <code>null</code>
	 */
	public void setTexts(List<Text> texts) {
		if (texts != null) {
			this.texts = new ArrayList<Text>(texts);
		}
		else {
			this.texts = new ArrayList<Text>();
		}
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
		return references;
	}

	/**
	 * @param references the references to set, may be <code>null</code>
	 */
	public void setReferences(List<Reference> references) {
		if (references != null) {
			this.references = new ArrayList<Reference>(references);
		}
		else {
			this.references = new ArrayList<Reference>();
		}
	}

	/**
	 * @param reference to add, may be <code>null</code> (nothing is added)
	 */
	public void addReference(Reference reference) {
		if (reference != null)
			getReferences().add(reference);
	}

	/**
	 * @param rasterBasis the rasterBasis to add, may be <code>null</code> (nothing is
	 * added)
	 */
	public void addRasterBasis(RasterBasis rasterBasis) {
		if (rasterBasis != null)
			getRasterBasis().add(rasterBasis);
	}

	/**
	 * @return all rasterBasis, may be empty but never <code>null</code>
	 */
	public List<RasterBasis> getRasterBasis() {
		return rasterBasis;
	}

	/**
	 * @param rasterBasis the rasterBasis to set, may be <code>null</code>
	 */
	public void setRasterBasis(List<RasterBasis> rasterBasis) {
		if (rasterBasis != null) {
			this.rasterBasis = new ArrayList<RasterBasis>(rasterBasis);
		}
		else {
			this.rasterBasis = new ArrayList<RasterBasis>();
		}
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
