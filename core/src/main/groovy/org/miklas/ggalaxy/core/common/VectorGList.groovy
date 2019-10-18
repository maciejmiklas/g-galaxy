package org.miklas.ggalaxy.core.common

import static org.miklas.ggalaxy.core.common.PointG.EMPTY_POINT

class VectorGList {
    final List<VectorG> list
    int limit
    int idx = 0

    VectorGList(int limit) {
        list = new ArrayList<>(limit)
        this.limit = limit
        1.upto(limit) {
            list << new VectorG()
        }
    }

    VectorGList leftShift(VectorG val) {
        if (idx >= limit) {
            idx = 0
        }
        list[idx++] << val
        return this
    }

    double getAngle() {
        double angle = 0
        int elements = 0
        list.each {
            if (it.start.x != EMPTY_POINT) {
                angle += it.angle
                elements++
            }
        }
        return angle / elements
    }

    VectorG.Direction getDirection() {
        def direction
        int cnt = 0
        VectorG.Direction.values()*.each {
            dr ->
                int cr = list.count { it.start.x != EMPTY_POINT && it.direction == dr }
                if (cr > cnt) {
                    cnt = cr
                    direction = dr
                }
        }
        return direction
    }

}
