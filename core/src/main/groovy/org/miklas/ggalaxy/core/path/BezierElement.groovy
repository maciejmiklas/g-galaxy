package org.miklas.ggalaxy.core.path

import groovy.transform.ToString
import org.miklas.ggalaxy.core.common.PointG

@ToString(includeNames = true, includePackage = false)
class BezierElement {
    /** First control point */
    PointG cp1

    /** Second control point */
    PointG cp2

    /** end element */
    PointG end
}
