package org.miklas.ggalaxy.core.common

import org.miklas.ggalaxy.core.AbstractTest
import spock.lang.Unroll

class PointTest extends AbstractTest {

    @Unroll
    def "Test distance"(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, int distance) {
        given:
        Point p1 = [x1, y1, w1, h1]
        Point p2 = [x2, y2, w2, h2]

        expect:
        p1.distance(p2) == distance

        where:
        x1 | y1 | w1 | h1 | x2   | y2   | w2 | h2 | distance
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

    @Unroll
    def "Test overlaps"(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, boolean overlaps) {
        given:
        Point p1 = [x1, y1, w1, h1]
        Point p2 = [x2, y2, w2, h2]

        expect:
        p1.overlaps(p2) == overlaps

        where:
        x1 | y1 | w1 | h1 | x2 | y2 | w2 | h2 | overlaps
        1  | 1  | 10 | 10 | 11 | 11 | 10 | 10 | false
        1  | 1  | 10 | 10 | 10 | 10 | 10 | 10 | true
        1  | 1  | 10 | 10 | 9  | 9  | 10 | 10 | true
        1  | 1  | 10 | 10 | 1  | 1  | 10 | 10 | true
        1  | 1  | 10 | 10 | 1  | 1  | 2  | 2  | true
        1  | 1  | 2  | 2  | 1  | 1  | 10 | 10 | true
        1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | true
        11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | true
    }

}
