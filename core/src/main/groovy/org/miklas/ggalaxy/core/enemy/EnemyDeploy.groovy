package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.cannon.MainCannon
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.common.CollisionDetection
import org.miklas.ggalaxy.core.common.Conf

class EnemyDeploy extends Actor {

    private final CollisionDetection collisionDetection
    private final List<EnemyShip> enemyList = []
    private final EnemyFactory[] enemyFactoryList
    private long lastSpawnTime = -1
    private MainCannon mainCannon

    EnemyDeploy(CollisionDetection collisionDetection, MainCannon mainCannon) {
        this.collisionDetection = collisionDetection
        this.mainCannon = mainCannon
        this.enemyFactoryList = [new AsteroidFactory(), new FighterFactory(mainCannon)]
    }

    private void spawn() {
        if (TimeUtils.millis() - lastSpawnTime < Conf.movement(AssetType.ENEMY_SHIP).spawnMs) {
            return
        }
        EnemyFactory enemyFactory = enemyFactoryList[lastSpawnTime % enemyFactoryList.length as int]
        def (EnemyShip enemy, boolean created) = enemyFactory.next()
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
