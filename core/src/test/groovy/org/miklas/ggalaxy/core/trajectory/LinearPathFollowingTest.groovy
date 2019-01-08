package org.miklas.ggalaxy.core.trajectory

import com.badlogic.gdx.math.Vector2
import spock.lang.Specification

class LinearPathFollowingTest extends Specification {

    def "distance calculation"(int x1, int y1, int x2, int y2, int distance) {
        given:
        LinearPathFollowing path = []

        expect:
        path.distance(new Vector2(x1, y1), new Vector2(x2, y2)) == distance

        where:
        x1 | y1 | x2   | y2  | distance
        0  | 0  | 1    | 1   | 1
        0  | 0  | 10   | 10  | 14
        0  | 0  | 1280 | 800 | 1509
        34 | 99 | 1280 | 800 | 1429
        0  | 0  | 0    | 0   | 0
        34 | 99 | 34   | 99  | 0
    }
}
