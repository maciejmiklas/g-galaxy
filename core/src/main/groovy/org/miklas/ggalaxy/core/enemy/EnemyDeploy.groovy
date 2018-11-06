package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.common.CollisionDetection
import org.miklas.ggalaxy.core.common.Conf

class EnemyDeploy extends Actor {

    private final CollisionDetection collisionDetection
    private final List<Enemy> enemyList = []
    private final EnemyFactory[] enemyFactoryList = [new AsteroidFactory(), new FighterFactory()]
    private long lastSpawnTime = -1

    EnemyDeploy(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection
    }

    private void spawn() {
        if (TimeUtils.millis() - lastSpawnTime < Conf.ins.enemyDeploy.spawnMs) {
            return
        }
        EnemyFactory enemyFactory = enemyFactoryList[lastSpawnTime % enemyFactoryList.length as int]
        def (Enemy enemy, boolean created) = enemyFactory.next()
        enemy.reset()

        if (created) {
            collisionDetection << enemy
            enemyList << enemy
        }
        lastSpawnTime = TimeUtils.millis()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        spawn()
        enemyList.forEach {
            it.draw batch
        }
    }

}
