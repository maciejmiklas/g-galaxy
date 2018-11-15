package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope

import static org.miklas.ggalaxy.core.common.AssetName.*

@PackageScope
class AsteroidFactory {

    private final List<Asteroid> asteroids = []
    private final def ASSETS = [[BOMB_BLUE, EXPLOSION_BLUE], [MINE_BLUE, EXPLOSION_BLUE], [MINE_RED, EXPLOSION_RED]]
    private int spawnIdx = 0

    def next() {
        Asteroid asteroid = asteroids.find { it.mode == Asteroid.Mode.INACTIVE }
        if (asteroid != null) {
            return [asteroid, false]
        }

        if (spawnIdx == ASSETS.size()) {
            spawnIdx = 0
        }
        def assets = ASSETS[spawnIdx++]
        asteroid = new Asteroid(assets[0], assets[1])
        asteroids << asteroid
        [asteroid, true]
    }
}