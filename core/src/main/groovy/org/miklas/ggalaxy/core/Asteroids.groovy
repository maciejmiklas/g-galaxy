package org.miklas.ggalaxy.core


import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils

import static org.miklas.ggalaxy.core.AnimationFactory.Asset.*

class Asteroids extends Actor implements Disposable {

    private final List<Asteroid> asteroids = []

    private final MainShip mainShip
    private final def MINE_ASSET = [[BOMB_BLUE, EXPLOSION_BLUE], [MINE_BLUE, EXPLOSION_BLUE], [MINE_RED, EXPLOSION_RED]]

    private int spawnIdx = 0
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
            def arr = MINE_ASSET[spawnIdx++]
            asteroids << new Asteroid(arr[0], arr[1])

            if(spawnIdx == MINE_ASSET.size()){
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
            it.draw batch, mainShip.position
        }
    }

    @Override
    void dispose() {

    }
}
