package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.cannon.Cannon
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle

class FighterDeploy extends Actor {

    private final List<Obstacle> enemyList = []
    private final FighterFactory fighterFactory
    private long lastSpawnTime = -1

    FighterDeploy(Cannon mainCannon) {
        this.fighterFactory = [mainCannon]
    }

    private void spawn() {
        if (TimeUtils.millis() - lastSpawnTime < Conf.movement(AssetType.ENEMY_SHIP).spawnMs) {
            return
        }
        def (Obstacle enemy, boolean created) = fighterFactory.next()
        enemy.reset()

        if (created) {
            enemyList << enemy
        }
        lastSpawnTime = TimeUtils.millis()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        spawn()
        enemyList.forEach {
            it.draw batch, parentAlpha
        }
    }

}
