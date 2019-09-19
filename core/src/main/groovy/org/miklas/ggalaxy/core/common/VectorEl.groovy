package org.miklas.ggalaxy.core.common

import groovy.transform.ToString

import static org.miklas.ggalaxy.core.common.VectorEl.Orientation.*

/** Vector is pointing from start to end: -[start]----[end]-> */
@ToString(includeNames = true, includePackage = false)
class VectorEl {

    Point start
    Point end

    VectorEl(Point start, Point end) {
        this.start = start
        this.end = end
    }

    int getLength() {
        Math.sqrt((start.x - end.x).pow2 + (start.y - end.y).pow2)
    }

    /**
     * @return angle in degrees to x-axis
     */
    int getAngle() {
        def angle
        if (orientation == LEFT_DOWN) {

        }

        return angle
    }

    def getOrientation() {
        def orientation
        if (start.x < end.x && start.y > end.y) {
            orientation = LEFT_DOWN

        } else if (start.x < end.x && start.y < end.y) {
            orientation = LEFT_UP

        } else if (start.x > end.x && start.y > end.y) {
            orientation = RIGHT_DOWN

        } else {
            orientation = RIGHT_UP
        }
        return orientation
    }

    enum Orientation {
        LEFT_DOWN,
        LEFT_UP,
        RIGHT_DOWN,
        RIGHT_UP
    }

}
