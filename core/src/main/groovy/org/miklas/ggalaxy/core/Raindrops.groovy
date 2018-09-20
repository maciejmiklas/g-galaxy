package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils
import groovy.transform.CompileStatic

@CompileStatic
class Raindrops implements Disposable, Renderable {

    private List<Raindrop> raindrops = []
    private long lastDropTime = -1
    private Sound dropSound
    private Bucket bucket

    Raindrops() {
        dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
    }

    void spawnRaindrop() {
        if (TimeUtils.nanoTime() - lastDropTime < 1000000000) {
            return
        }

        // lists are nicely integrated into Groovy, so you can use += for example
        // alternative to spare the GC: raindrops.add(new Rectangle(...))
        raindrops << new Raindrop()
        lastDropTime = TimeUtils.nanoTime()
    }

    @Override
    void render(Batch batch, Camera camera) {

        // move the raindrops, play sound effects
        raindrops.removeAll { drop ->
            boolean remove = false
            if (!drop.move()) {
                remove = true
            }

            if (drop.overlaps(bucket.position)) {
                dropSound.play()
                remove = true
            }
            if (!remove) {
                drop.render batch, camera
            }
            return remove
        }
    }

    @Override
    void dispose() {
        dropSound.dispose()
    }
}
