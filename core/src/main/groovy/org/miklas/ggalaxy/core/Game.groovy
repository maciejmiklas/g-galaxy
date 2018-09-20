package org.miklas.ggalaxy.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static Conf.X_RES
import static Conf.Y_RES

@CompileStatic
class Game implements ApplicationListener {

    OrthographicCamera camera
    SpriteBatch batch
    Music rainMusic
    List<Disposable> disposable
    List<Renderable> renderable
    Raindrops raindrops
    ClearScr clearScr
    Bucket bucket
    Background background

    @Override
    void create() {

        camera = []
        batch = []
        disposable = []
        clearScr = []
        bucket = []
        renderable = []
        raindrops = [bucket: bucket]
        background = []

        // load the drop sound effect and the rain clearScr "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"))

        // start the playback of the clearScr music immediately
        //rainMusic.looping = true
        // rainMusic.play()

        // create the camera and the SpriteBatch
        camera.setToOrtho(false, X_RES, Y_RES)

        // spawn the first raindrop
        raindrops.spawnRaindrop()

        disposable << bucket << raindrops << rainMusic << batch << Raindrop.disposable() << background
        renderable = [clearScr, background, bucket, raindrops] as List<Renderable>
    }

    @Override
    void render() {
        // tell the camera to update its matrices, then
        // tell the SpriteBatch to render in the camera's coordinate system
        camera.update()
        batch.projectionMatrix = camera.combined

        // begin a new batch and draw the bucket and all drops
        batch.begin()
        renderable.each { it.render(batch, camera) }
        batch.end()

        // check if we need to create a new raindrop
        raindrops.spawnRaindrop()
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

