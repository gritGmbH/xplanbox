package de.latlon.xplan.manager.web.client.i18n;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface DynamicXPlanWebMessages extends ConstantsWithLookup {

    String communityColumn();

    String filterCommunityLabel();

    String filterCommunityTooltip();

    String filterCommunitySelectionAll();

    String filterCommunitySelectionOther();

    String unauthorizedCommunity_Import();

    String unauthorizedCommunity_Delete();

    String unauthorizedCommunity_PublishingPlu();

}