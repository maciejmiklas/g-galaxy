package org.miklas.ggalaxy.core.common

import groovy.transform.ToString
import groovy.transform.TupleConstructor

import static java.lang.Math.abs
import static org.miklas.ggalaxy.core.common.VectorG.Direction.*

/** Vector is pointing from start to end: -[start]----[end]-> */
@ToString(includeNames = true, includePackage = false)
class VectorG {

    PointG start
    PointG end
    PointG p90 = []

    VectorG() {
        this([], [])
    }

    VectorG(PointG start, PointG end) {
        this.start = start
        this.end = end
    }

    VectorG leftShift(VectorG val) {
        start << val.start
        end << val.end
        return this
    }

    int getLength() {
        getLength(start, end)
    }

    // TODO do not use sqrt, it's slow!
    double getLength(PointG start, PointG end) {
        Math.sqrt((start.x - end.x).pow2 + (start.y - end.y).pow2)
    }

    VectorG clone() {
        PointG nstart = []
        nstart << start

        PointG nend = []
        nend << end
        return new VectorG(nstart, nend)
    }

    /**
     * see /core/src/test/groovy/org/miklas/ggalaxy/core/common/rotation.heic
     *
     * @return angle in degrees to x-axis, -1 if cannot determine
     */
    double getAngle() {
        PointG p90 = getP90()
        double aLen = getLength p90, end
        double bLen = getLength start, p90

        if (bLen == 0 || bLen == 0) {
            return -1
        }
        return abs(Math.toDegrees(Math.atan(aLen / bLen)) + direction.angle)
    }

    PointG getP90() {
        if (direction == LEFT_DOWN || direction == RIGHT_DOWN) {
            p90.x = start.x
            p90.y = end.y
        } else {
            p90.x = end.x
            p90.y = start.y
        }
        return p90
    }

    /**
     * see /core/src/test/groovy/org/miklas/ggalaxy/core/common/direction.jpg
     */
    Direction getDirection() {
        def orientation

        if (start.x == end.x && start.y < end.y) {
            orientation = UP

        } else if (start.y == end.y && start.x < end.x) {
            orientation = RIGHT

        } else if (start.x == end.x && start.y > end.y) {
            orientation = DOWN

        } else if (start.y == end.y && start.x > end.x) {
            orientation = LEFT

        } else if (start.x < end.x && start.y < end.y) {
            orientation = RIGHT_UP

        } else if (start.x < end.x && start.y > end.y) {
            orientation = RIGHT_DOWN

        } else if (start.x > end.x && start.y > end.y) {
            orientation = LEFT_DOWN

        } else if (start.x > end.x && start.y < end.y) {
            orientation = LEFT_UP

        } else {
            throw new IllegalArgumentException("Cannot determine orientation for: " + start + ", " + end)
        }
        return orientation
    }

    /**
     * see /core/src/test/groovy/org/miklas/ggalaxy/core/common/rotation.heic
     */
    @TupleConstructor
    enum Direction {
        UP(0),
        LEFT_UP(-90),

        LEFT(90),
        LEFT_DOWN(-180),

        DOWN(180),
        RIGHT_DOWN(180),

        RIGHT(270),
        RIGHT_UP(270)

        int angle
    }

}
