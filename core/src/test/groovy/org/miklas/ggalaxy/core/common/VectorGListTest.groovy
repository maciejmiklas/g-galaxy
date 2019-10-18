package org.miklas.ggalaxy.core.common

import org.miklas.ggalaxy.core.AbstractTest

import static org.miklas.ggalaxy.core.common.VectorG.Direction.*

class VectorGListTest extends AbstractTest {

    def "Test angle"() {
        given:
        VectorG v1 = [new PointG(40, 80), new PointG(20, 60)]
        VectorG v2 = [new PointG(40, 60), new PointG(20, 80)]
        VectorG v3 = [new PointG(20, 80), new PointG(40, 60)]
        VectorG v4 = [new PointG(20, 80), new PointG(90, 60)]

        VectorGList vl = new VectorGList(20)
        vl << v1 << v1 << v3 << v4

        expect:
        v1.direction == RIGHT_DOWN
        v2.direction == RIGHT_UP
        v3.direction == LEFT_DOWN
        v4.direction == LEFT_DOWN

        v1.angle == 135
        v2.angle == 45
        v3.angle == 225
        v4.angle == 255

        vl.idx == 4
        vl.direction == LEFT_DOWN

    }
}
