package org.miklas.ggalaxy.core.path

import groovy.transform.ToString
import org.miklas.ggalaxy.core.common.Point2D

@ToString(includeNames = true, includePackage = false)
class BezierElement {
    /** First control point */
    Point2D cp1

    /** Second control point */
    Point2D cp2

    /** end element */
    Point2D end
}
