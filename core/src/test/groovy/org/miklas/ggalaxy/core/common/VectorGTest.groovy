package org.miklas.ggalaxy.core.common

import org.miklas.ggalaxy.core.AbstractTest
import spock.lang.Unroll

import static org.miklas.ggalaxy.core.common.VectorG.Direction.*

class VectorGTest extends AbstractTest {

    @Unroll
    def "Test clone"() {
        given:
        VectorG vector = [new PointG(10, 20), new PointG(30, 40)]
        VectorG cloned = vector.clone()
        vector.start.x = 0
        vector.start.y = 1
        vector.end.x = 2
        vector.end.y = 3

        expect:
        cloned.start.x == 10
        cloned.start.y == 20
        cloned.end.x == 30
        cloned.end.y == 40
    }

    // see rotation.heic
    @Unroll
    def "Test angle"(int sx, int sy, int ex, int ey, int angle, VectorG.Direction orientation) {
        given:
        VectorG vector = [new PointG(sx, sy), new PointG(ex, ey)]

        expect:
        vector.direction == orientation
        vector.angle == angle

        where:
        sx | sy | ex | ey | angle    | orientation
        40 | 80 | 20 | 60 | 180 - 45 | RIGHT_DOWN
        20 | 60 | 40 | 80 | 45 + 270 | LEFT_UP
        40 | 60 | 20 | 80 | 45       | RIGHT_UP
        20 | 80 | 40 | 60 | 270 - 45 | LEFT_DOWN
        20 | 80 | 30 | 60 | 270 - 63 | LEFT_DOWN
        20 | 80 | 50 | 60 | 270 - 33 | LEFT_DOWN
        20 | 80 | 90 | 60 | 270 - 15 | LEFT_DOWN
    }

    // see orientation.heic
    @Unroll
    def "Test orientation"(int sx, int sy, int ex, int ey, VectorG.Direction orientation) {
        given:
        VectorG vector = [new PointG(sx, sy), new PointG(ex, ey)]

        expect:
        vector.direction == orientation

        where:
        sx | sy  | ex | ey  | orientation
        20 | 110 | 20 | 120 | UP
        20 | 110 | 30 | 120 | RIGHT_UP
        20 | 110 | 30 | 110 | RIGHT
        20 | 110 | 30 | 100 | RIGHT_DOWN
        20 | 110 | 20 | 100 | DOWN
        20 | 110 | 10 | 100 | LEFT_DOWN
        20 | 110 | 10 | 110 | LEFT
        20 | 110 | 10 | 120 | LEFT_UP

    }

    @Unroll
    def "Test length"(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, int length) {
        given:
        PointG p1 = [x1, y1, w1, h1]
        PointG p2 = [x2, y2, w2, h2]
        VectorG vector = [p1, p2]
        expect:
        vector.length == length

        where:
        x1 | y1 | w1 | h1 | x2   | y2   | w2 | h2 | length
        1  | 1  | 10 | 10 | 1    | 1    | 10 | 10 | 0
        0  | 0  | 10 | 10 | 0    | 0    | 10 | 10 | 0
        0  | 0  | 0  | 0  | 0    | 0    | 0  | 0  | 0
        1  | 1  | 10 | 10 | 2    | 2    | 10 | 10 | 1
        1  | 1  | 10 | 10 | 22   | 22   | 10 | 10 | 29
        1  | 1  | 10 | 10 | 22   | 22   | 1  | 1  | 29
        1  | 1  | 0  | 0  | 22   | 22   | 0  | 0  | 29
        0  | 0  | 0  | 0  | 22   | 22   | 0  | 0  | 31
        0  | 0  | 0  | 0  | 10   | 10   | 0  | 0  | 14
        0  | 0  | 0  | 0  | 1000 | 1000 | 0  | 0  | 1414
    }
}

