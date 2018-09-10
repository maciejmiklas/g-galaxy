package org.miklas.drop.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils
import groovy.transform.CompileStatic

import static org.miklas.drop.core.Conf.X_RES
import static org.miklas.drop.core.Conf.Y_RES

@CompileStatic
class DropGame implements ApplicationListener {

    OrthographicCamera camera
    SpriteBatch batch

    Sound dropSound
    Music rainMusic

    List<Raindrop> raindrops
    List<Disposable> disposable
    long lastDropTime

    Background background
    Bucket bucket

    @Override
    void create() {

        camera = []
        batch = []
        raindrops = []
        disposable = []
        background = []
        bucket = []

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"))

        // start the playback of the background music immediately
        //rainMusic.looping = true
        // rainMusic.play()

        // create the camera and the SpriteBatch
        camera.setToOrtho(false, X_RES, Y_RES)

        // spawn the first raindrop
        spawnRaindrop()

        disposable << bucket << dropSound << rainMusic << batch << Raindrop.disposable()
    }

    def spawnRaindrop() {
        if (TimeUtils.nanoTime() - lastDropTime < 1000000000) {
            return
        }

        // lists are nicely integrated into Groovy, so you can use += for example
        // alternative to spare the GC: raindrops.add(new Rectangle(...))
        raindrops << new Raindrop()
        lastDropTime = TimeUtils.nanoTime()
    }

    @Override
    void render() {
        background.render()

        // tell the camera to update its matrices, then
        // tell the SpriteBatch to render in the camera's coordinate system
        camera.update()
        batch.projectionMatrix = camera.combined

        // begin a new batch and draw the bucket and all drops
        batch.begin()
        raindrops.each { drop -> drop.render batch }
        bucket.render batch
        batch.end()

        // process user input
        bucket.processUserInput(camera)

        // check if we need to create a new raindrop
        spawnRaindrop()

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
            return remove
        }
    }


    @Override
    void dispose() {
        disposable*.dispose()
    }

    @Override
    void resize(int x, int y) {
    }

    @Override
    void pause() {
    }

    @Override
    void resume() {
    }
}

