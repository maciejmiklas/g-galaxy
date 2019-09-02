package org.miklas.ggalaxy.core

import groovy.transform.PackageScope
import groovyx.gpars.GParsPool
import org.miklas.ggalaxy.core.common.Asset
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType
import org.springframework.stereotype.Component

@PackageScope
@Component
class CollisionDetection {
    final int w = Conf.SCR_WIDTH
    final int w2 = w / 2
    final int h = Conf.SCR_HEIGHT
    final int h2 = h / 2
    List<Asset> elements = []

    CollisionDetection() {
        EventBus.register(EventType.OBSTACLE_CREATED) { elements << it }
    }

    void leftShift(Asset obstacle) {
        elements << obstacle
    }

    void detect() {
        List<List<Asset>> split = split()
        GParsPool.withPool {
            split.eachParallel {
                process it
            }
        }
    }

    void process(List<Asset> obstacles) {
        for (int extIdx = 0; extIdx < obstacles.size() - 1; extIdx++) {
            Asset extObs = obstacles.get extIdx
            for (int intIdx = extIdx + 1; intIdx < obstacles.size(); intIdx++) {
                Asset intObst = obstacles.get intIdx
                if (extObs.checkCollision(intObst)) {
                    extObs.hit intObst
                    intObst.hit extObs
                }
            }
        }
    }

    List<List<Asset>> split() {
        def split = []

        // (0,0) - (640,380) - left bottom corner
        split[0] = elements.findAll {
            it.position.x >= 0 && it.position.x < w2 && it.position.y >= 0 && it.position.y < h2
        }

        // (640, 0) - (1280,380) - right bottom corner
        split[1] = elements.findAll {
            filter1 it.position.x, it.position.y
        }
        split[1].addAll split[0].findAll {
            filter1 it.position.x + it.position.width, it.position.y + it.position.height
        }

        // (0,380) - (640, 760) - left top corner
        split[2] = elements.findAll {
            filter2 it.position.x, it.position.y
        }
        split[2].addAll split[0].findAll {
            filter2 it.position.x + it.position.width, it.position.y + it.position.height
        }

        // (640, 380) - (1280, 760) - right top corner
        split[3] = elements.findAll {
            filter3 it.position.x, it.position.y
        }
        split[3].addAll split[0].findAll {
            filter3 it.position.x + it.position.width, it.position.y + it.position.height
        }
        split[3].addAll split[1].findAll {
            filter3 it.position.x + it.position.width, it.position.y + it.position.height
        }
        split[3].addAll split[2].findAll {
            filter3 it.position.x + it.position.width, it.position.y + it.position.height
        }
        return split
    }

    boolean filter3(def x, def y) {
        x >= w2 && x < w && y >= h2 && y < h
    }

    boolean filter2(def x, def y) {
        x >= 0 && x < w2 && y >= h2 && y < h
    }

    boolean filter1(def x, def y) {
        x >= w2 && x < w && y >= 0 && y < h2
    }
}
