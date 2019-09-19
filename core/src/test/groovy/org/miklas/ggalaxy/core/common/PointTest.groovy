package org.miklas.ggalaxy.core.common

import org.miklas.ggalaxy.core.AbstractTest
import spock.lang.Unroll

class PointTest extends AbstractTest {

    @Unroll
    def "Test leftShift"(int x, int y, int width, int height, int ox, int oy, int owidth, int oheight) {
        given:
        Point point = [10, 20, 200, 300]

        expect:
        Point res = point << new Point(x, y, width, height)
        res.x == ox
        res.y == oy
        res.width == owidth
        res.height == oheight

        where:
        x  | y  | width | height | ox | oy | owidth | oheight
        -1 | -1 | -1    | -1     | 10 | 20 | 200    | 300
        25 | -1 | -1    | -1     | 25 | 20 | 200    | 300
        -1 | 22 | -1    | -1     | 10 | 22 | 200    | 300
        -1 | -1 | 43    | -1     | 10 | 20 | 43     | 300
        -1 | -1 | -1    | 4545   | 10 | 20 | 200    | 4545
        1  | 2  | 3     | 4      | 1  | 2  | 3      | 4
    }

    @Unroll
    def "Test multiply"(int num, int ox, int oy) {
        given:
        Point point = [10, 20, 200, 300]

        expect:
        Point res = point * num
        res.x == ox
        res.y == oy
        res.width == 200
        res.height == 300

        where:
        num | ox | oy
        0   | 0  | 0
        5   | 50 | 100
    }

    @Unroll
    def "Test plus"(int x, int y, int ox, int oy) {
        given:
        Point point = [10, 20, 200, 300]

        expect:
        Point res = point + new Point(x, y, 5, 5)
        res.x == ox
        res.y == oy
        res.width == 200
        res.height == 300

        where:
        x | y | ox | oy
        0 | 0 | 10 | 20
        5 | 6 | 15 | 26
    }

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
