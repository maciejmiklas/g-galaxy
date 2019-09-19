package org.miklas.ggalaxy.core.common

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class Point {
    public final static int EMPTY_POINT = -1

    int x = EMPTY_POINT
    int y = EMPTY_POINT
    int width = EMPTY_POINT
    int height = EMPTY_POINT

    Point(int x, int y) {
        this.x = x
        this.y = y
    }

    Point(int x, int y, int width, int height) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    Point() {

    }

    boolean overlaps(Point point) {
        return x < point.x + point.width && x + width > point.x && y < point.y + point.height && y + height > point.y;
    }

    void reset() {
        x = EMPTY_POINT
        y = EMPTY_POINT
    }

    Point plus(Point val) {
        new Point(x: x + val.x, y: y + val.y, width: width, height: height)
    }

    Point multiply(Number val) {
        new Point(x: x * val, y: y * val, width: width, height: height)
    }

    Point leftShift(Point val) {
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

    int distance(Point to) {
        Math.sqrt((to.x - x).pow2 + (to.y - y).pow2)
    }
}
