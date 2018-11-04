package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils

import static org.miklas.ggalaxy.core.AnimationFactory.Asset.*

class Asteroids extends Actor implements Disposable {

    private final List<Asteroid> asteroids = []
    private final Sound dropSound
    private final MainShip mainShip
    private final def MINE_ASSET = [SPACE_BOMB_BLUE, SPACE_MINE_BLUE, SPACE_MINE_RED]

    private long lastDropTime = -1

    Asteroids(MainShip bucket) {
        this.mainShip = bucket
        dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
    }

    void spawnRaindrop() {
        if (TimeUtils.nanoTime() - lastDropTime < 1000000000) {
            return
        }
        asteroids << new Asteroid(MINE_ASSET[lastDropTime % MINE_ASSET.size() - 1 as int])
        lastDropTime = TimeUtils.nanoTime()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        // move the raindrops, play sound effects
        asteroids.removeAll { drop ->
            boolean remove = false
            if (!drop.move()) {
                remove = true
            }

            if (drop.overlaps(mainShip.position)) {
                dropSound.play()
                remove = true
            }
            if (!remove) {
                drop.draw batch, parentAlpha
            }
            return remove
        }
    }

    @Override
    void dispose() {
        dropSound.dispose()
    }
}
