package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.Conf
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

import static com.badlogic.gdx.utils.TimeUtils.millis
import static org.miklas.ggalaxy.core.common.Conf.cfg

@Component
class EnemyDeploy extends Actor {

    final List<Enemy> enemies = []
    long lastSpawnMs = -1
    long lastShuffleMs = -1
    int[][] lastX = [[-1, -1], [-1, -1]]
    int lastXIdx = 0

    @PostConstruct
    void init() {
        cfg.enemy.asset.each {
            AssetName an = it.key
            def clazz = it.value.clazz
            1.upto(it.value.max) {
                enemies.add this.class.classLoader.loadClass(clazz).newInstance(an, null)//TODO XXXXXXXXX
            }
        }
    }

    void spawn() {
        def ms = millis()

        if (ms - lastShuffleMs > cfg.enemy.shuffleEnemiesMs) {
            Collections.shuffle enemies
            lastShuffleMs = ms
        }

        if (ms - lastSpawnMs < cfg.enemy.spawnMs) {
            return
        }
        lastSpawnMs = ms
        int x = nextX()
        if (x < 0) {
            return
        }
        Enemy next = enemies.find { it.mode == Enemy.Mode.INACTIVE }
        if (next == null) {
            return
        }

        next.deploy x, Conf.SCR_HEIGHT + cfg.enemy.marginWidth
    }

   int nextX() {
        if (lastXIdx == lastX.length) {
            lastXIdx = 0
        }
        int x = MathUtils.random cfg.enemy.marginWidth, Conf.SCR_WIDTH - cfg.enemy.marginWidth

        if (lastX.find({ x > it[0] && x < it[1] })) {
            return -1
        }

        lastX[lastXIdx] = [x - cfg.enemy.deployDistance, x + cfg.enemy.deployDistance]
        lastXIdx++
        return x
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        spawn()
        enemies.forEach {
            it.draw batch, parentAlpha
        }
    }

    class Amount {
        int max
        int now
    }


}
