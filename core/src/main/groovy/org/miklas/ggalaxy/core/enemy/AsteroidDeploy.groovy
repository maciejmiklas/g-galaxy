package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle

class AsteroidDeploy extends Actor {

    private final List<Obstacle> enemyList = []
    private final AsteroidFactory asteroidFactory = []
    private long lastSpawnTime = -1

    private void spawn() {
        if (TimeUtils.millis() - lastSpawnTime < Conf.movement(AssetType.ENEMY_SHIP).spawnMs) {
            return
        }
        def (Obstacle enemy, boolean created) = asteroidFactory.next()
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
