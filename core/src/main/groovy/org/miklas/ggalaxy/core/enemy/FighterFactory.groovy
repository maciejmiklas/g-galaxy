package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.cannon.Cannon

import static org.miklas.ggalaxy.core.common.AssetName.*

@PackageScope
class FighterFactory {

    private final List<Fighter> fighters = []
    private final def ASSETS = [[SHIP_1_BLUE, EXPLOSION_BLUE], [SHIP_2_BLUE, EXPLOSION_BLUE], [SHIP_2_RED, EXPLOSION_RED]]
    private int spawnIdx = 0
    private Cannon mainCannon

    FighterFactory(Cannon mainCannon) {
        this.mainCannon = mainCannon
    }

    def next() {
        Fighter fighter = fighters.find { it.mode == Asteroid.Mode.INACTIVE }
        if (fighter != null) {
            return [fighter, false]
        }

        if (spawnIdx == ASSETS.size()) {
            spawnIdx = 0
        }
        def assets = ASSETS[spawnIdx++]
        fighter = new Fighter(assets[0], assets[1], mainCannon)
        fighters << fighter
        [fighter, true]
    }
}