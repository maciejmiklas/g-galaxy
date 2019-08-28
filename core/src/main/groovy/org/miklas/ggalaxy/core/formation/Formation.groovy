package org.miklas.ggalaxy.core.formation

import groovy.transform.ImmutableBase
import groovy.transform.ToString
import org.miklas.ggalaxy.core.path.PathFollowing

@ImmutableBase
@ToString(includeNames = true, includePackage = false)
class Formation {

    List<PathFollowing> paths = []

    void reset() {

    }
}
