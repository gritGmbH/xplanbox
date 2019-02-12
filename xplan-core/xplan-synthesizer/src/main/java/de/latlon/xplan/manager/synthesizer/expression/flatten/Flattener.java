package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.TypedObjectNode;

public interface Flattener {

    public boolean accepts( TypedObjectNode element );

    public String flatten( TypedObjectNode element );

}
