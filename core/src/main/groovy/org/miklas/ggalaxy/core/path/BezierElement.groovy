package org.miklas.ggalaxy.core.path

import groovy.transform.ToString
import org.miklas.ggalaxy.core.common.Point

@ToString(includeNames = true, includePackage = false)
class BezierElement {
    /** First control point */
    Point cp1

    /** Second control point */
    Point cp2

    /** end element */
    Point end
}
