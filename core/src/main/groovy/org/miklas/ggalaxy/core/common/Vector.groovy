package org.miklas.ggalaxy.core.common

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class Vector {

    Point start
    Point end

    Vector(Point start, Point end) {
        this.start = start
        this.end = end
    }

    int getLength() {
        Math.sqrt((start.x - end.x).pow2 + (start.y - end.y).pow2)
    }

}
