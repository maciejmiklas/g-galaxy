package org.miklas.ggalaxy.core

import com.badlogic.gdx.graphics.g2d.Batch
import org.miklas.ggalaxy.core.common.Asset
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.common.Point
import spock.lang.Unroll

import static org.miklas.ggalaxy.core.common.AssetType.*

class CollisionDetectionTest extends AbstractTest {

    def "Test split - Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

    def "Test split - Rocks in Region 0"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion0 detection
        List<Asset>[] splits = detection.split()

        then:
        splits[1] == []
        splits[2] == []
        splits[3] == []
        splits[0].size() == 2
        splits[0]*.position.width == [20, 20]
    }

    def "Test split - Rocks in Region 1"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion1 detection
        List<Asset>[] splits = detection.split()

        then:
        splits[0] == []
        splits[2] == []
        splits[3] == []
        splits[1].size() == 2
        splits[1]*.position.width == [21, 21]
    }

    def "Test split - Rock in Region 2"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion2 detection
        List<Asset>[] splits = detection.split()

        then:
        splits[0] == []
        splits[1] == []
        splits[3] == []
        splits[2].size() == 1
        splits[2]*.position.width == [22]
    }

    def "Test split - Rock in Region 3"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion3 detection
        List<Asset>[] splits = detection.split()

        then:
        splits[0] == []
        splits[1] == []
        splits[2] == []
        splits[3].size() == 1
        splits[3]*.position.width == [23]
    }

    def "Test split - Rocks in all Regions"() {

        given:
        CollisionDetection detection = []

        when:
        addRegion0 detection
        addRegion1 detection
        addRegion2 detection
        addRegion3 detection
        List<Asset>[] splits = detection.split()

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
    def "Test split - Rock on border between #b1 and #b2"(int b1, int b2, int x, int y, int notInA, int notInB, int notInC, int inZone) {
        CollisionDetection detection = []
        detection << new Rock(position: [x, y, 12, 12])
        List<Asset>[] splits = detection.split()

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

    def "Test split - Rock on border between 2 and 3"() {
        given:
        CollisionDetection detection = []

        when:
        detection << new Rock(position: [630, 400, 12, 12])
        List<Asset>[] splits = detection.split()

        then:
        splits[0].size() == 0
        splits[1].size() == 0

        splits[2].size() == 1
        splits[2]*.position.x == [630]

        splits[3].size() == 1
        splits[3]*.position.x == [630]
    }

    @Unroll
    def "Test split - Rock crossing border #b1 and #b2"(int b1, int b2, int x, int y, int notInA, int notInB, int inY, int inZ) {

        CollisionDetection detection = []
        detection << new Rock(position: [x, y, 20, 20])
        List<Asset>[] splits = detection.split()

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

    def "Test process - rock detection"() {
        given:
        List<Rock> rocks = createTestRocks()
        CollisionDetection detection = []
        detection.process(rocks)

        expect:
        rocks[0].hits[0].position.x == 15
        rocks[0].hits[1].position.x == 17

        rocks[1].hits[0].position.x == 0
        rocks[1].hits[1].position.x == 30
        rocks[1].hits[2].position.x == 17

        rocks[2].hits[0].position.x == 15

        rocks[3].hits.empty

        rocks[4].hits[0].position.x == 0
        rocks[4].hits[1].position.x == 15
    }

    def addRegion0(def detection) {
        detection << new Rock(position: [0, 0, 20, 20])
        detection << new Rock(position: [500, 200, 20, 20])
    }

    def addRegion1(def detection) {
        detection << new Rock(position: [641, 1, 21, 21])
        detection << new Rock(position: [1000, 200, 21, 21])
    }

    def addRegion2(def detection) {
        detection << new Rock(position: [10, 400, 22, 22])
    }

    def addRegion3(def detection) {
        detection << new Rock(position: [700, 400, 23, 23])
    }

    List<Rock> createTestRocks() {
        [new Rock(position: [0, 0, 20, 20], type: ASTEROID),
         new Rock(position: [15, 15, 60, 60], type: SPACE_SHIP),
         new Rock(position: [30, 30, 20, 20], type: ASTEROID),
         new Rock(position: [100, 80, 2, 2], type: SHOT),
         new Rock(position: [17, 17, 2, 2], type: SHOT)]
    }

    class Rock implements Asset {
        Point position = []
        AssetType type = ASTEROID
        List<Asset> hits = []

        @Override
        boolean checkCollision(Asset other) {
            return position.overlaps(other.position)
        }

        @Override
        void hit(Asset other) {
            hits << other
        }

        @Override
        void draw(Batch batch, float parentAlpha) {
        }
    }
}
