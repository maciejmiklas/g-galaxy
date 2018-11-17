package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.cannon.Cannon

import static org.miklas.ggalaxy.core.common.AssetName.*

@PackageScope
class FighterFactory implements EnemyFactory {

    private final List<Fighter> fighters = []
    private final def ASSETS = [[SHIP_CARGO, EXPLOSION_BLUE], [SHIP_INTERCEPTOR_BLUE, EXPLOSION_BLUE], [SHIP_INTERCEPTOR_RED, EXPLOSION_RED]]
    private int spawnIdx = 0
    private Cannon mainCannon

    FighterFactory(Cannon mainCannon) {
        this.mainCannon = mainCannon
    }

    @Override
    NextEnemy next() {
        Fighter fighter = fighters.find { it.mode == Fighter.Mode.INACTIVE }
        if (fighter != null) {
            return [asset: fighter, newInstance: false]
        }

        if (spawnIdx == ASSETS.size()) {
            spawnIdx = 0
        }
        def assets = ASSETS[spawnIdx++]
        fighter = new Fighter(assets[0], assets[1], mainCannon)
        fighters << fighter
        return [asset: fighter, newInstance: true]
    }
}