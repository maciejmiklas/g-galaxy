package org.miklas.ggalaxy.core.common

import groovy.transform.ToString

@ToString(includeNames = false, includePackage = false)
class PointG {
    public final static int EMPTY_POINT = -1

    int x = EMPTY_POINT
    int y = EMPTY_POINT
    int width = EMPTY_POINT
    int height = EMPTY_POINT

    PointG(int x, int y) {
        this.x = x
        this.y = y
    }

    PointG(int x, int y, int width, int height) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    PointG() {

    }

    PointG clone() {
        PointG np = []
        np << this
        return np
    }

    boolean overlaps(PointG point) {
        return x < point.x + point.width && x + width > point.x && y < point.y + point.height && y + height > point.y;
    }

    void reset() {
        x = EMPTY_POINT
        y = EMPTY_POINT
    }

    PointG plus(PointG val) {
        new PointG(x: x + val.x, y: y + val.y, width: width, height: height)
    }

    PointG multiply(Number val) {
        new PointG(x: x * val, y: y * val, width: width, height: height)
    }

    PointG leftShift(PointG val) {
        if (val.x != EMPTY_POINT) {
            x = val.x
        }

        if (val.y != EMPTY_POINT) {
            y = val.y
        }

        if (val.height != EMPTY_POINT) {
            height = val.height
        }

        if (val.width != EMPTY_POINT) {
            width = val.width
        }

        return this
    }

    int distance(PointG to) {
        Math.sqrt((to.x - x).pow2 + (to.y - y).pow2)
    }
}
