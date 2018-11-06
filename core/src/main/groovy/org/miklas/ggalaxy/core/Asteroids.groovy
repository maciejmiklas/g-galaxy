package org.miklas.ggalaxy.core


import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils

import static org.miklas.ggalaxy.core.AnimationFactory.Asset.*

class Asteroids extends Actor implements Disposable {

    private final List<Asteroid> asteroids = []

    private final MainShip mainShip
    private final def MINE_ASSET = [BOMB_BLUE, MINE_BLUE, MINE_RED,]

    private long lastSpawnTime = -1

    Asteroids(MainShip ship) {
        this.mainShip = ship
    }

    void spawn() {
        if (TimeUtils.nanoTime() - lastSpawnTime < Conf.ins.asteroid.spawn.time) {
            return
        }

        Asteroid free = asteroids.find { it.mode == Asteroid.Mode.INACTIVE }
        if (free == null) {
            asteroids << new Asteroid(MINE_ASSET[lastSpawnTime % MINE_ASSET.size() - 1 as int], EXPLOSION_BLUE)
        } else {
            free.reset()
        }
        lastSpawnTime = TimeUtils.nanoTime()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        asteroids.forEach {
            it.draw batch,  mainShip.position
        }
    }

    @Override
    void dispose() {

    }
}
