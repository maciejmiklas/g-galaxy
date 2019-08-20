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

    Point2D plus(Point2D val) {
        new Point2D(x: x + val.x, y: y + val.y)
    }

    Point2D multiply(Number val) {
        new Point2D(x: x * val.x, y: y * val.y)
    }

    Point2D leftShift(Point2D val) {
        x = val.x
        y = val.y
        this
    }


    int distance(Point2D to) {
        Math.sqrt((to.x - x).pow2 + (to.y - y).pow2)
    }
}
