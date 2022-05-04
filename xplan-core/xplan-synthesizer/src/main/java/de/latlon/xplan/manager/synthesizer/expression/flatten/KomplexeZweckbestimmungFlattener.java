package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class KomplexeZweckbestimmungFlattener extends AbstractFlattener {

	private static final Map<String, String> acceptedZweckbestimmungenAndEnums = new HashMap<>();

	static {
		acceptedZweckbestimmungenAndEnums.put("BP_KomplexeZweckbestGemeinschaftsanlagen",
				"BP_ZweckbestimmungGemeinschaftsanlagen");
		acceptedZweckbestimmungenAndEnums.put("BpKomplexeZweckbestSpielSportanlage",
				"XP_ZweckbestimmungSpielSportanlage");
		acceptedZweckbestimmungenAndEnums.put("BP_KomplexeZweckbestNebenanlagen", "BP_ZweckbestimmungNebenanlagen");
		acceptedZweckbestimmungenAndEnums.put("BP_KomplexeZweckbestLandwirtschaft", "XP_ZweckbestimmungLandwirtschaft");
		acceptedZweckbestimmungenAndEnums.put("BP_KomplexeZweckbestGruen", "XP_ZweckbestimmungGruen");
		acceptedZweckbestimmungenAndEnums.put("BP_KomplexeZweckbestGemeinbedarf", "XP_ZweckbestimmungGemeinbedarf");
		acceptedZweckbestimmungenAndEnums.put("BP_KomplexeZweckbestVerEntsorgung", "XP_ZweckbestimmungVerEntsorgung");
		acceptedZweckbestimmungenAndEnums.put("FP_KomplexeZweckbestGemeinbedarf", "XP_ZweckbestimmungGemeinbedarf");
		acceptedZweckbestimmungenAndEnums.put("FP_KomplexeZweckbestSpielSportanlage",
				"XP_ZweckbestimmungSpielSportanlage");
		acceptedZweckbestimmungenAndEnums.put("FP_KomplexeZweckbestGruen", "XP_ZweckbestimmungGruen");
		acceptedZweckbestimmungenAndEnums.put("FP_KomplexeZweckbestLandwirtschaft", "XP_ZweckbestimmungLandwirtschaft");
		acceptedZweckbestimmungenAndEnums.put("FP_KomplexeZweckbestWald", "XP_ZweckbestimmungWald");
		acceptedZweckbestimmungenAndEnums.put("FP_KomplexeZweckbestVerEntsorgung", "XP_ZweckbestimmungVerEntsorgung");
		acceptedZweckbestimmungenAndEnums.put("", "");
		acceptedZweckbestimmungenAndEnums.put("", "");
		acceptedZweckbestimmungenAndEnums.put("", "");
		acceptedZweckbestimmungenAndEnums.put("", "");
	}

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, acceptedZweckbestimmungenAndEnums.keySet());
	}

	@Override
	public String flatten(TypedObjectNode node) {
		QName elNodeName = ((ElementNode) node).getName();
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(elNodeName);
		List<Pair<String, String>> properties = new ArrayList<>();
		appendTranslatedCode("Allgemein", node, "allgemein", version,
				acceptedZweckbestimmungenAndEnums.get(elNodeName.getLocalPart()), properties);
		append("Textliche Ergänzung", node, "textlicheErgaenzung", properties);
		append("Aufschrift", node, "aufschrift", properties);
		return encode(properties);
	}

}
