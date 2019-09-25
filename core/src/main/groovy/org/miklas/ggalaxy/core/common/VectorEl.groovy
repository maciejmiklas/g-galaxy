package org.miklas.ggalaxy.core.common

import groovy.transform.ToString

import static org.miklas.ggalaxy.core.common.VectorEl.Direction.*

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
        getLength(start, end)
    }

    int getLength(Point start, Point end) {
        Math.sqrt((start.x - end.x).pow2 + (start.y - end.y).pow2)
    }

    /**
     * @return angle in degrees to x-axis
     */
    int getAngle() {
        Point p90
        int aLen
        int bLen

        if (orientation == LEFT_DOWN || orientation == RIGHT_DOWN) {
            p90 = [start.x, end.y]
            aLen = getLength start, p90
            bLen = getLength p90, end

        } else {
            p90 = [end.x, start.y]
            aLen = getLength p90, end
            bLen = getLength p90, start
        }
        return Math.toDegrees(Math.atan(aLen / bLen))
    }

    def getOrientation() {
        def orientation
        if (start.x < end.x && start.y < end.y) {
            orientation = LEFT_UP

        } else if (start.x < end.x && start.y > end.y) {
            orientation = LEFT_DOWN

        } else if (start.x > end.x && start.y < end.y) {
            orientation = RIGHT_UP

        } else {
            orientation = RIGHT_DOWN
        }
        return orientation
    }

    enum Direction {
        LEFT_UP,
        LEFT_DOWN,
        RIGHT_UP,
        RIGHT_DOWN
    }

}
