package org.miklas.ggalaxy.core.common

import org.miklas.ggalaxy.core.AbstractTest
import spock.lang.Unroll

import static org.miklas.ggalaxy.core.common.VectorEl.Direction.*

class VectorElTest extends AbstractTest {

    // see angle.jpg
    @Unroll
    def "Test angle"(int sx, int sy, int ex, int ey, int angle, VectorEl.Direction orientation) {
        given:
        VectorEl vector = [new Point(sx, sy), new Point(ex, ey)]

        expect:
        vector.orientation == orientation
        vector.angle == angle

        where:
        sx | sy | ex | ey | angle | orientation
        40 | 80 | 20 | 60 | 45    | RIGHT_DOWN
        20 | 60 | 40 | 80 | 45    | LEFT_UP
        40 | 60 | 20 | 80 | 45    | RIGHT_UP
        20 | 80 | 40 | 60 | 45    | LEFT_DOWN
        20 | 80 | 30 | 60 | 63    | LEFT_DOWN
        20 | 80 | 50 | 60 | 33    | LEFT_DOWN
    }

    // see orientation.jpg
    @Unroll
    def "Test orientation"(int sx, int sy, int ex, int ey, VectorEl.Direction orientation) {
        given:
        VectorEl vector = [new Point(sx, sy), new Point(ex, ey)]

        expect:
        vector.orientation == orientation

        where:
        sx | sy  | ex | ey  | orientation
        20 | 400 | 40 | 300 | LEFT_DOWN
        20 | 100 | 40 | 200 | LEFT_UP
        80 | 400 | 60 | 300 | RIGHT_DOWN
        80 | 100 | 60 | 200 | RIGHT_UP
    }

    @Unroll
    def "Test length"(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, int length) {
        given:
        Point p1 = [x1, y1, w1, h1]
        Point p2 = [x2, y2, w2, h2]
        VectorEl vector = [p1, p2]
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
