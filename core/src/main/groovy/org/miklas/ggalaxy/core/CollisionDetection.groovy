package org.miklas.ggalaxy.core

class CollisionDetection {
    private final int w = Conf.SCR_WIDTH
    private final int w2 = w / 2
    private final int h = Conf.SCR_HEIGHT
    private final int h2 = h / 2

    private List<Obstacle> elements = []

    void leftShift(Obstacle obstacle) {
        elements << obstacle
    }

    void detect() {

    }

    List<Obstacle>[] split() {
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
        split
    }

    private boolean filter3(def x, def y) {
        x >= w2 && x < w && y >= h2 && y < h
    }

    private boolean filter2(def x, def y) {
        x >= 0 && x < w2 && y >= h2 && y < h
    }

    private boolean filter1(def x, def y) {
        x >= w2 && x < w && y >= 0 && y < h2
    }
}
