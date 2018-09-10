package org.miklas.drop.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils
import groovy.transform.CompileStatic

@CompileStatic
class Drop implements ApplicationListener {

    static int X_RES = 1280
    static int Y_RES = 760

    OrthographicCamera camera
    SpriteBatch batch

    Texture dropImage
    Texture bucketImage
    Sound dropSound
    Music rainMusic

    List<Rectangle> raindrops
    List<Disposable> disposable

    Rectangle bucket
    long lastDropTime

    Vector3 touchPos

    @Override
    void create() {

        camera = []
        batch = []
        touchPos = []
        raindrops = []
        disposable = []

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = [Gdx.files.internal("assets/drop.png")]
        bucketImage = [Gdx.files.internal("assets/bucket.png")]

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"))

        // start the playback of the background music immediately
        rainMusic.looping = true
        rainMusic.play()

        // create the camera and the SpriteBatch
        camera.setToOrtho(false, X_RES, Y_RES)

        // create a Rectangle to logically represent the bucket
        // note the division by floats, which is much faster than by ints in Groovy
        bucket = [X_RES / 2f - 64 / 2f as float, 20, 64, 64]

        // spawn the first raindrop
        spawnRaindrop()

        disposable << dropImage << bucketImage << dropSound << rainMusic << batch
    }

    def spawnRaindrop() {
        if (TimeUtils.nanoTime() - lastDropTime < 1000000000) {
            return
        }

        // lists are nicely integrated into Groovy, so you can use += for example
        // alternative to spare the GC: raindrops.add(new Rectangle(...))
        raindrops << new Rectangle(MathUtils.random(0, X_RES - 64), Y_RES, 64, 64)
        lastDropTime = TimeUtils.nanoTime()
    }

    @Override
    void render() {
        clearScreen()

        // tell the camera to update its matrices, then
        // tell the SpriteBatch to render in the camera's coordinate system
        camera.update()
        batch.projectionMatrix = camera.combined

        // begin a new batch and draw the bucket and all drops
        batch.begin()
        batch.draw bucketImage, bucket.x, bucket.y
        raindrops.each { drop -> batch.draw(dropImage, drop.x, drop.y) }
        batch.end()

        // process user input
        processUserInput()

        // make sure the bucket stays within the screen bounds
        if (bucket.x < 0) {
            bucket.x = 0
        }
        if (bucket.x > X_RES - 64) {
            bucket.x = X_RES - 64
        }

        // check if we need to create a new raindrop
        spawnRaindrop()

        // move the raindrops, play sound effects
        raindrops.removeAll {
            it.y -= 200 * Gdx.graphics.deltaTime
            boolean remove = false
            if (it.y + 64 < 0) {
                remove = true
            }

            if (it.overlaps(bucket)) {
                dropSound.play()
                remove = true
            }
            return remove
        }
    }

    def clearScreen() {
        Gdx.gl.glClearColor 0, 0, 0.2f, 1
        Gdx.gl.glClear GL20.GL_COLOR_BUFFER_BIT
    }

    def processUserInput() {
        if (Gdx.input.touched) {
            touchPos.set Gdx.input.x, Gdx.input.y, 0
            camera.unproject touchPos
            bucket.x = touchPos.x - 64 / 2f as float
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bucket.x -= 200 * Gdx.graphics.deltaTime
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bucket.x += 200 * Gdx.graphics.deltaTime
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

