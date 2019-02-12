package de.latlon.xplan.manager.codelists;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * Encapsulates the translation of an internal XPlan 2 code to it's corresponding XPlan Syn code.
 * <p>
 * Note that the name of code list in the translation may differ from the input (e.g. input code list BP_Art and output
 * BP_PlanArt) and that the translation may actually result in two codes, e.g. if a value of 2200 (=BoeschungsFlaeche)
 * from code list XP_ZweckbestimmungGruen is translated, the first syn code is 2400 (=SpezGruenflaeche) for code list
 * XP_ZweckbestimmungGruen, while the second is 24001 (=BoeschungsFlaeche) for code list
 * XP_BesondereZweckbestimmungGruen.
 * </p>
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author: schneider $
 * 
 * @version $Revision: 566 $, $Date: 2010-01-08 17:35:04 +0100 (Fr, 08 Jan 2010) $
 */
public class XPlan2CodeTranslation {

    private String xplan2CodeList;

    private String xplan2Code;

    private String xplanSynCodeList;

    private String xplanSynCode;

    private String xplanSynExtCodeList;

    private String xplanSynExtCode;

    XPlan2CodeTranslation( String xplan2CodeList, String xplan2Code, String xplanSynCodeList, String xplanSynCode ) {
        this.xplan2CodeList = xplan2CodeList;
        this.xplan2Code = xplan2Code;
        this.xplanSynCodeList = xplanSynCodeList;
        this.xplanSynCode = xplanSynCode;
    }

    XPlan2CodeTranslation( String xplan2CodeList, String xplan2Code, String xplanSynCodeList, String xplanSynCode,
                           String xplanSynExtCodeList, String xplanSynExtCode ) {
        this.xplan2CodeList = xplan2CodeList;
        this.xplan2Code = xplan2Code;
        this.xplanSynCodeList = xplanSynCodeList;
        this.xplanSynCode = xplanSynCode;
        this.xplanSynExtCodeList = xplanSynExtCodeList;
        this.xplanSynExtCode = xplanSynExtCode;
    }

    String getXplan2CodeList() {
        return xplan2CodeList;
    }

    String getXplan2Code() {
        return xplan2Code;
    }

    public String getXplanSynCodeList() {
        return xplanSynCodeList;
    }

    public String getXplanSynCode() {
        return xplanSynCode;
    }

    public String getXplanSynExtCodeList() {
        return xplanSynExtCodeList;
    }

    public String getXplanSynExtCode() {
        return xplanSynExtCode;
    }

    @Override
    public String toString() {
        String s = XPlanCodeListsFactory.get( XPlanVersion.XPLAN_2 ).getDescription( xplan2CodeList, xplan2Code ) + " (" + xplan2Code
                   + ") -> " + XPlanCodeListsFactory.getXPlanSyn().getDescription( xplanSynCodeList, xplanSynCode )
                   + " (" + xplanSynCode + ")";
        if ( xplanSynExtCode != null ) {
            s += ", " + XPlanCodeListsFactory.getXPlanSyn().getDescription( xplanSynExtCodeList, xplanSynExtCode )
                 + " (" + xplanSynExtCode + "), ext list:  " + xplanSynExtCodeList;
        }
        return s;
    }
}
