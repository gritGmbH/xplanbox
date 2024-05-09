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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.TEXT_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.XL_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.XS_LENGTH;

import java.io.Serializable;

/**
 * Encapsulates the text of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class Text extends AbstractReference implements Serializable {

	@Valid
	private String featureId;

	@Size(max = XS_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String key;

	@Size(max = S_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String basis;

	@Size(max = XL_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String text;

	@Valid
	private TextRechtscharacterType rechtscharakter;

	public Text() {
	}

	/**
	 * @param featureId id of the feature member, never <code>null</code>
	 */
	public Text(String featureId) {
		this.featureId = featureId;
	}

	/**
	 * @param featureId id of the feature member, never <code>null</code>
	 * @param reference reference, may be <code>null</code>
	 */
	public Text(String featureId, String reference) {
		super(reference, null);
		this.featureId = featureId;
	}

	/**
	 * @param featureId id of the feature member, never <code>null</code>
	 * @param key key, may be <code>null</code>
	 * @param basis basis, may be <code>null</code>
	 * @param text text, may be <code>null</code>
	 * @param reference reference, may be <code>null</code>
	 * @param geoReference geoReference, may be <code>null</code>
	 */
	public Text(String featureId, String key, String basis, String text, String reference, String geoReference) {
		super(reference, geoReference);
		this.featureId = featureId;
		this.key = key;
		this.basis = basis;
		this.text = text;
	}

	/**
	 * @param featureId id of the feature member, never <code>null</code>
	 * @param key key, may be <code>null</code>
	 * @param basis basis, may be <code>null</code>
	 * @param text text, may be <code>null</code>
	 * @param reference reference, may be <code>null</code>
	 * @param geoReference geoReference, may be <code>null</code>
	 * @param rechtscharakter rechtscharakter, may be <code>null</code>
	 */
	public Text(String featureId, String key, String basis, String text, TextRechtscharacterType rechtscharakter,
			String reference, String geoReference) {
		super(reference, geoReference);
		this.featureId = featureId;
		this.key = key;
		this.basis = basis;
		this.text = text;
		this.rechtscharakter = rechtscharakter;
	}

	/**
	 * @return the key, may be <code>null</code>
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set, may be <code>null</code>
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the basis, may be <code>null</code>
	 */
	public String getBasis() {
		return basis;
	}

	/**
	 * @param basis the basis to set, may be <code>null</code>
	 */
	public void setBasis(String basis) {
		this.basis = basis;
	}

	/**
	 * @return the text, may be <code>null</code>
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set, may be <code>null</code>
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the id of the feature member describing this text, may be <code>null</code>
	 */
	public String getFeatureId() {
		return featureId;
	}

	/**
	 * @param featureId the id of the feature member describing this text, may be
	 * <code>null</code>
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	/**
	 * @return the rechtscharakter, may be <code>null</code>
	 */
	public TextRechtscharacterType getRechtscharakter() {
		return rechtscharakter;
	}

	/**
	 * @param rechtscharakter the rechtscharakter to set, may be <code>null</code>
	 */
	public void setRechtscharakter(TextRechtscharacterType rechtscharakter) {
		this.rechtscharakter = rechtscharakter;
	}

	@Override
	public String toString() {
		return "Text {featureId=" + featureId + ", key=" + key + ", basis=" + basis + ", text=" + text
				+ ", rechtscharakter= " + rechtscharakter + "}";
	}

}
