package org.miklas.ggalaxy.core.common

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@ToString(includeNames = true, includePackage = false)
@TupleConstructor
class Point2D {
    public final static int EMPTY_POINT = -1

    int x = EMPTY_POINT
    int y = EMPTY_POINT

    void reset() {
        x = EMPTY_POINT
        y = EMPTY_POINT
    }

    int distance(Point2D to) {
        Math.sqrt((to.x - x).pow2 + (to.y - y).pow2)
    }
}
