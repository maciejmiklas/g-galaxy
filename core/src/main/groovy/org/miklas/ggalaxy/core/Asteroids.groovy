package org.miklas.ggalaxy.core

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils

import static org.miklas.ggalaxy.core.AnimationFactory.Asset.*

class Asteroids extends Actor {

    private final List<Asteroid> asteroids = []
    private final def MINE_ASSET = [[BOMB_BLUE, EXPLOSION_BLUE], [MINE_BLUE, EXPLOSION_BLUE], [MINE_RED, EXPLOSION_RED]]
    private final CollisionDetection obstacles

    private int spawnIdx = 0
    private long lastSpawnTime = -1

    Asteroids(CollisionDetection obstacles) {
        this.obstacles = obstacles
    }

    void spawn() {
        if (TimeUtils.nanoTime() - lastSpawnTime < Conf.ins.asteroid.spawn.time) {
            return
        }

        Asteroid free = asteroids.find { it.mode == Asteroid.Mode.INACTIVE }
        if (free == null) {
            def assets = MINE_ASSET[spawnIdx++]
            def asteroid = new Asteroid(assets[0], assets[1])
            asteroids << asteroid
            obstacles << asteroid

            if (spawnIdx == MINE_ASSET.size()) {
                spawnIdx = 0
            }
        } else {
            free.reset()
        }
        lastSpawnTime = TimeUtils.nanoTime()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        asteroids.forEach {
            it.draw batch
        }
    }

}
