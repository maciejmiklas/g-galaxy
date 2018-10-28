package org.miklas.ggalaxy.core

import com.badlogic.gdx.math.Rectangle
import spock.lang.Specification
import spock.lang.Unroll

class CollisionDetectionTest extends Specification {

    def "Split - Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

    def "Split - Rocks in Region 0"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion0 detection
        List<Obstacle>[] splits = detection.split()

        then:
        splits[1] == []
        splits[2] == []
        splits[3] == []
        splits[0].size() == 2
        splits[0]*.position.width == [20, 20]
    }

    def "Split - Rocks in Region 1"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion1 detection
        List<Obstacle>[] splits = detection.split()

        then:
        splits[0] == []
        splits[2] == []
        splits[3] == []
        splits[1].size() == 2
        splits[1]*.position.width == [21, 21]
    }

    def "Split - Rock in Region 2"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion2 detection
        List<Obstacle>[] splits = detection.split()

        then:
        splits[0] == []
        splits[1] == []
        splits[3] == []
        splits[2].size() == 1
        splits[2]*.position.width == [22]
    }

    def "Split - Rock in Region 3"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion3 detection
        List<Obstacle>[] splits = detection.split()

        then:
        splits[0] == []
        splits[1] == []
        splits[2] == []
        splits[3].size() == 1
        splits[3]*.position.width == [23]
    }

    def "Split - Rocks in all Regions"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion0 detection
        addRegion1 detection
        addRegion2 detection
        addRegion3 detection
        List<Obstacle>[] splits = detection.split()

        then:
        splits[0].size() == 2
        splits[0]*.position.width == [20, 20]

        splits[1].size() == 2
        splits[1]*.position.width == [21, 21]

        splits[2].size() == 1
        splits[2]*.position.width == [22]

        splits[3].size() == 1
        splits[3]*.position.width == [23]
    }


    @Unroll
    def "Split - Rock on border between #b1 and #b2"(int b1, int b2, int x, int y, int notInA, int notInB, int notInC, int inZone) {
        CollisionDetection detection = []
        detection << new Rock(position: [x, y, 12, 12])
        List<Obstacle>[] splits = detection.split()

        expect:
        splits[notInA].size() == 0
        splits[notInB].size() == 0
        splits[notInC].size() == 0

        splits[inZone].size() == 1
        splits[inZone]*.position.x == [x]

        where:
        b1 | b2 | x   | y   | notInA | notInB | notInC | inZone
        0  | 1  | 640 | 300 | 0      | 3      | 3      | 1
        0  | 2  | 30  | 380 | 0      | 1      | 3      | 2
        1  | 3  | 640 | 380 | 0      | 1      | 2      | 3
    }

    def "Split - Rock on border between 2 and 3"() {
        given:
        CollisionDetection detection = []

        when:
        detection << new Rock(position: [630, 400, 12, 12])
        List<Obstacle>[] splits = detection.split()

        then:
        splits[0].size() == 0
        splits[1].size() == 0

        splits[2].size() == 1
        splits[2]*.position.x == [630]

        splits[3].size() == 1
        splits[3]*.position.x == [630]
    }

    @Unroll
    def "Split - Rock crossing border #b1 and #b2"(int b1, int b2, float x, float y, int notInA, int notInB, int inY, int inZ) {

        CollisionDetection detection = []
        detection << new Rock(position: [x, y, 20, 20])
        List<Obstacle>[] splits = detection.split()

        expect:
        splits[notInA].size() == 0
        splits[notInB].size() == 0

        splits[inY].size() == 1
        splits[inY]*.position.x == [x]

        splits[inZ].size() == 1
        splits[inZ]*.position.width == [20]

        where:
        b1 | b2 | x   | y   || notInA | notInB | inY | inZ
        0  | 1  | 635 | 100 || 2      | 3      | 0   | 1
        0  | 2  | 100 | 370 || 1      | 3      | 0   | 2
        0  | 3  | 630 | 370 || 1      | 2      | 0   | 3
        1  | 3  | 700 | 370 || 0      | 2      | 1   | 3
    }

    private def addRegion0(def detection) {
        detection << new Rock(position: [0, 0, 20, 20])
        detection << new Rock(position: [500, 200, 20, 20])
    }

    private def addRegion1(def detection) {
        detection << new Rock(position: [641, 1, 21, 21])
        detection << new Rock(position: [1000, 200, 21, 21])
    }

    private def addRegion2(def detection) {
        detection << new Rock(position: [10, 400, 22, 22])
    }

    private def addRegion3(def detection) {
        detection << new Rock(position: [700, 400, 23, 23])
    }

    class Rock implements Obstacle {
        Rectangle position = []
        final Type type = Type.ASTEROID

        @Override
        boolean collision(Rectangle other) {
            return position.overlaps(other)
        }

        @Override
        void hit(Type other) {

        }
    }
}
