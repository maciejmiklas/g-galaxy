package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.CyclicList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

import static com.badlogic.gdx.utils.TimeUtils.millis
import static org.miklas.ggalaxy.core.common.Conf.cfg

@Component
class EnemyDeploy extends Actor {

    @Autowired
    private AsteroidFactory _asteroidFactory

    @Autowired
    private FighterFactory _fighterFactory

    private final List<Deployable> enemyList = []
    private long lastSpawnTime = -1
    private CyclicList<EnemyFactory> factories
    private int[][] lastX = [[-1, -1], [-1, -1]]
    private int lastXIdx = 0

    @PostConstruct
    void init() {
        factories = [_asteroidFactory, _asteroidFactory, _fighterFactory]
    }

    private void spawn() {
        if (millis() - lastSpawnTime < cfg.enemyDeploy.spawnMs) {
            return
        }
        int x = nextX()
        if (x < 0) {
            return
        }
        EnemyFactory.NextEnemy next = factories.next().next()
        next.asset.deploy nextX(), Conf.SCR_HEIGHT + 30

        if (next.newInstance) {
            enemyList << next.asset
        }
        lastSpawnTime = millis()
    }

    private nextX() {
        if (lastXIdx == lastX.length) {
            lastXIdx = 0
        }
        int x = MathUtils.random cfg.enemyDeploy.widthMargin, Conf.SCR_WIDTH - cfg.enemyDeploy.widthMargin

        if (lastX.find({ x > it[0] && x < it[1] })) {
            println "$x, $lastX"
            return -1
        }

        lastX[lastXIdx] = [x - cfg.enemyDeploy.deployMargin, x + cfg.enemyDeploy.deployMargin]
        lastXIdx++
        return x
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        spawn()
        enemyList.forEach {
            it.draw batch, parentAlpha
        }
    }

}
