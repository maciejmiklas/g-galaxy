package org.miklas.ggalaxy.core.common

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class Point2D {
    public final static int EMPTY_POINT = -1

    int x = EMPTY_POINT
    int y = EMPTY_POINT

    void reset() {
        x = EMPTY_POINT
        y = EMPTY_POINT
    }
}
